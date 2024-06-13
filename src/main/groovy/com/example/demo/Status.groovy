package com.example.demo

enum Status { //enum also throws error

    WaitingToBeProcessed,
    InProcess,
    Complete,
    Failed,

    static Status[] getStatusNotCompleteOrFailed() {
        values() - [Complete, Failed]
    }
}
