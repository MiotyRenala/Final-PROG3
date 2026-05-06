
CREATE TABLE IF NOT EXISTS collectivity_activity (
    id VARCHAR(36) PRIMARY KEY,
    collectivity_id VARCHAR(36) NOT NULL REFERENCES collectivity(id) ON DELETE CASCADE,
    label VARCHAR(255) NOT NULL,
    activity_type activity_type_enum,
    member_occupation_concerned TEXT NOT NULL,
    recurrence_week_ordinal INTEGER CHECK (recurrence_week_ordinal BETWEEN 1 AND 5),
    recurrence_day_of_week VARCHAR(2) CHECK (recurrence_day_of_week IN ('MO', 'TU', 'WE', 'TH', 'FR', 'SA', 'SU')),
    executive_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_activity_type CHECK (
        (recurrence_week_ordinal IS NOT NULL AND recurrence_day_of_week IS NOT NULL AND executive_date IS NULL)
        OR
        (recurrence_week_ordinal IS NULL AND recurrence_day_of_week IS NULL AND executive_date IS NOT NULL)
    )
);


CREATE TYPE activity_type_enum AS ENUM ('MEETING', 'TRAINING', 'OTHER');
