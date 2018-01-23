package com.woolf.cleanapp;


import com.woolf.cleanapp.di.ComponentManager;
import com.woolf.cleanapp.di.app.AppComponent;
import com.woolf.cleanapp.di.app.DaggerAppComponent;
import com.woolf.cleanapp.di.app.module.ContextModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

public class CleanAppRule implements TestRule {

    public CleanAppRule() {
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                AppComponent appComponent = DaggerAppComponent.builder()
                        .contextModule(new ContextModule(Mockito.mock(CleanApplication.class)))
                        .utilsModule(new TestUtilsModule())
                        .build();
                ComponentManager.getInstance().setAppComponent(appComponent);
                base.evaluate();
            }
        };
    }
}
