-- ============================================================
-- V8: Cosmere RPG — Character Sheet Schema
-- Схема для листов персонажей настольной ролевой игры
-- ============================================================

CREATE SCHEMA IF NOT EXISTS cosmere;

-- ------------------------------------------------------------
-- ITEMS — Элементы листа: ancestry, culture, path, action
-- Хранит как фиксированные поля, так и type-specific данные (JSONB)
-- ------------------------------------------------------------
CREATE TABLE cosmere.items
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name              VARCHAR(255) NOT NULL,
    type              VARCHAR(50)  NOT NULL, -- ANCESTRY | CULTURE | PATH | ACTION
    item_key          VARCHAR(100),          -- 'human', 'alethi', 'agent', 'drop'
    img               VARCHAR(500),
    description_value TEXT,
    description_short TEXT,
    system_data       JSONB,                 -- events, activation, advancement и т.д.
    created_at        TIMESTAMP        DEFAULT NOW()
);

CREATE INDEX idx_items_type     ON cosmere.items (type);
CREATE INDEX idx_items_item_key ON cosmere.items (item_key);

-- ------------------------------------------------------------
-- CHARACTERS — Лист персонажа
-- Атрибуты/ресурсы/защиты хранятся как плоские колонки
-- для простых SELECT и UPDATE без JOIN
-- ------------------------------------------------------------
CREATE TABLE cosmere.characters
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name      VARCHAR(255) NOT NULL,
    tier      INT              DEFAULT 1,
    size      VARCHAR(50)      DEFAULT 'medium',
    img       VARCHAR(500),

    -- Attributes (6 атрибутов × value + bonus)
    attr_str_value INT DEFAULT 1, attr_str_bonus INT DEFAULT 0,
    attr_spd_value INT DEFAULT 1, attr_spd_bonus INT DEFAULT 0,
    attr_int_value INT DEFAULT 1, attr_int_bonus INT DEFAULT 0,
    attr_wil_value INT DEFAULT 1, attr_wil_bonus INT DEFAULT 0,
    attr_awa_value INT DEFAULT 1, attr_awa_bonus INT DEFAULT 0,
    attr_pre_value INT DEFAULT 1, attr_pre_bonus INT DEFAULT 0,

    -- Resources (здоровье, фокус, инвеститура)
    res_hea_value INT DEFAULT 0, res_hea_max_override INT, res_hea_bonus INT DEFAULT 0,
    res_foc_value INT DEFAULT 0, res_foc_max_override INT, res_foc_bonus INT DEFAULT 0,
    res_inv_value INT DEFAULT 0, res_inv_max_override INT, res_inv_bonus INT DEFAULT 0,

    -- Defenses (физическая, когнитивная, духовная)
    def_phy_override INT, def_phy_bonus INT DEFAULT 0,
    def_cog_override INT, def_cog_bonus INT DEFAULT 0,
    def_spi_override INT, def_spi_bonus INT DEFAULT 0,

    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- ------------------------------------------------------------
-- CHARACTER_SKILLS — Навыки персонажа
-- Отдельная таблица: навыков много (~23), они часто обновляются
-- ------------------------------------------------------------
CREATE TABLE cosmere.character_skills
(
    character_id UUID        NOT NULL REFERENCES cosmere.characters (id) ON DELETE CASCADE,
    skill_key    VARCHAR(10) NOT NULL, -- 'agi', 'lwp', 'ded', 'adh' ...
    rank         INT              DEFAULT 0,
    mod_override INT,
    mod_bonus    INT              DEFAULT 0,
    unlocked     BOOLEAN          DEFAULT TRUE, -- false для магических навыков (adh, grv...)
    PRIMARY KEY (character_id, skill_key)
);

-- ------------------------------------------------------------
-- CHARACTER_ITEMS — Связь M:N персонаж ↔ item
-- Один item (например 'Human' ancestry) может быть у многих персонажей
-- ------------------------------------------------------------
CREATE TABLE cosmere.character_items
(
    character_id UUID      NOT NULL REFERENCES cosmere.characters (id) ON DELETE CASCADE,
    item_id      UUID      NOT NULL REFERENCES cosmere.items (id) ON DELETE CASCADE,
    equipped_at  TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (character_id, item_id)
);

CREATE INDEX idx_char_items_char ON cosmere.character_items (character_id);
CREATE INDEX idx_char_items_item ON cosmere.character_items (item_id);
