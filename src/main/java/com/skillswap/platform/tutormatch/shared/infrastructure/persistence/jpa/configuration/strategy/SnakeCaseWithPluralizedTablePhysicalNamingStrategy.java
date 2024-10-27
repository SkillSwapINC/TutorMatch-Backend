package com.skillswap.platform.tutormatch.shared.infrastructure.persistence.jpa.configuration.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

/**
 * Snake Case With Pluralized Table Physical Naming Strategy
 * @summary
 * PhysicalNamingStrategy implementation that converts entity names to snake_case and table names to pluralized snake_case.
 *
 * @since 1.0.0
 */
public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Convert identifier to snake case
     * @param identifier Identifier
     * @return Identifier
     */
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
                .replaceAll(regex, replacement)
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }

    /**
     * Convert identifier to plural
     * @param identifier Identifier
     * @return Identifier
     */
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}
