package project.shimozukuri.datastoremicroservice.service;

public interface CDCEventConsumer {

    void handle(
            String message
    );

}
