package com.github.vbauer.yta.model;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * Model represents the result of translation.
 *
 * @author Vladislav Bauer
 */

@SuppressWarnings("serial")
@Immutable
public abstract class Translation implements Serializable {

    /**
     * Get translation direction.
     *
     * @return direction
     */
    @Parameter(order = 0)
    public abstract Direction direction();

    /**
     * Get translated text.
     *
     * @return translated text
     */
    @Parameter(order = 2)
    public abstract String text();


    /**
     * A factory method to create translation object using direction and translated text.
     *
     * @param direction translation direction
     * @param text translated text
     * @return translation
     */
    @Nonnull
    public static Translation of(final Direction direction, final String text) {
        return ImmutableTranslation.of(direction, text);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return text();
    }

}
