package me.piggypiglet.botchecker.core.framework;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Injector;
import me.piggypiglet.botchecker.BotCheckerBootstrap;
import me.piggypiglet.botchecker.core.tasks.Task;
import org.reflections.Reflections;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BinderModule extends AbstractModule {
    private final Class main;
    private Injector injector;

    public BinderModule(Class main) {
        this.main = main;
    }

    public Injector createInjector() {
        injector = com.google.inject.Guice.createInjector(this);
        return injector;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure() {
        bind(main).toInstance(main);

        // injector is always made before configure is ran, so this shouldn't be needed, but guice wants it anyway.
        Task.async(r -> {
            //noinspection StatementWithEmptyBody
            while (!BotCheckerBootstrap.isReady()) {}

            bindings();
        });
    }

    private void bindings() {
        bind(Injector.class).annotatedWith(Guice.class).toInstance(injector);
        bind(org.reflections.Reflections.class).annotatedWith(Reflections.class).toInstance(new org.reflections.Reflections("me.piggypiglet.botchecker"));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface Guice {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @BindingAnnotation
    public @interface Reflections {}
}
