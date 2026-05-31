-- Конвертируем system_data из TEXT в JSONB.
-- CASE-ом обрабатываем NULL и пустые строки (могли остаться от старого пустого create()).
ALTER TABLE cosmere.items
    ALTER COLUMN system_data TYPE JSONB
    USING CASE
        WHEN system_data IS NULL OR system_data = '' THEN NULL
        ELSE system_data::jsonb
    END;

-- GIN-индекс на всю system_data jsonb.
-- Ускоряет операторы @>, ?, ?&, ?|, нужные для поиска внутри:
--   WHERE system_data->'traits' ? 'thrown'                  — оружие с trait thrown
--   WHERE system_data->'damage'->>'type' = 'keen'           — оружие с damage type keen
--   WHERE system_data @> '{"type":"heavy_wpn"}'             — все heavy_wpn
CREATE INDEX IF NOT EXISTS idx_items_system_data_gin
    ON cosmere.items USING gin (system_data);
