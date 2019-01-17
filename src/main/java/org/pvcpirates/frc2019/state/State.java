package org.pvcpirates.frc2019.state;

import org.pvcpirates.frc2019.Status;

public abstract class State {
    public Status status;

    public abstract void init();

    public abstract void exec();

    public abstract void stop();
}
