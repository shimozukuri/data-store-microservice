package project.shimozukuri.datastoremicroservice.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.shimozukuri.datastoremicroservice.config.RedisSchema;
import project.shimozukuri.datastoremicroservice.model.MeasurementType;
import project.shimozukuri.datastoremicroservice.model.Summary;
import project.shimozukuri.datastoremicroservice.model.SummaryType;
import project.shimozukuri.datastoremicroservice.repository.SummaryRepository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SummaryRepositoryImpl implements SummaryRepository {

    private final JedisPool jedisPool;

    @Override
    public Optional<Summary> findBySensorId(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    ) {
        try(Jedis jedis = jedisPool.getResource()) {
            if (!jedis.sismember(
                    RedisSchema.sensorKeys(),
                    String.valueOf(sensorId)
            )) {
                Optional.empty();
            }

            if (measurementTypes.isEmpty() && !summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        Set.of(MeasurementType.values()),
                        summaryTypes,
                        jedis
                );
            } else if (!measurementTypes.isEmpty() && summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        Set.of(SummaryType.values()),
                        jedis
                );
            } else {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        summaryTypes,
                        jedis
                );
            }
        }
    }

    private Optional<Summary> getSummary(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes,
            Jedis jedis
    ) {
        Summary summary = new Summary();
        summary.setSensorId(sensorId);

        for (MeasurementType mType : measurementTypes) {
            for (SummaryType sType : summaryTypes) {
                Summary.SummaryEntry entry = new Summary.SummaryEntry();
                entry.setType(sType);

                String value = jedis.hget(
                        RedisSchema.summaryKey(sensorId, mType),
                        sType.name().toLowerCase()
                );

                if (value != null) {
                    entry.setValue(Double.parseDouble(value));
                }

                summary.addValue(mType, entry);
            }
        }

        return Optional.of(summary);
    }
}
