-- =====================================================
-- V1 — Items table
-- Flyway sets search_path = cosmere automatically
-- =====================================================

CREATE TABLE IF NOT EXISTS items
(
    id                UUID         NOT NULL DEFAULT gen_random_uuid(),
    name              VARCHAR(255) NOT NULL,
    type              VARCHAR(50)  NOT NULL,
    item_key          VARCHAR(100),
    img               TEXT,
    description_value TEXT,
    description_short VARCHAR(1000),
    system_data       TEXT,
    created_at        TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at        TIMESTAMP    NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_items PRIMARY KEY (id)
);

CREATE INDEX idx_items_type ON items (type);
CREATE INDEX idx_items_name ON items (name);
