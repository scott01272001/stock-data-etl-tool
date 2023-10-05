CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- CREATE TABLE account
-- (
--     id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
--     email           VARCHAR(255) NOT NULL,
--     password        VARCHAR(255) NOT NULL,
--     create_datetime TIMESTAMP WITH TIME ZONE,
--     update_datetime TIMESTAMP WITH TIME ZONE
-- );

CREATE SEQUENCE task_seq INCREMENT by 1;
CREATE TABLE task
(
    id          INTEGER PRIMARY KEY default nextval('task_seq'),
    type        varchar(255) not null,
    activate    boolean      not null,
    schedule    jsonb        not null,
    task_config jsonb        not null
);


-- CREATE SEQUENCE schedule_task_seq INCREMENT by 1;
-- CREATE TABLE schedule_task
-- (
--     schedule_task_id INTEGER PRIMARY KEY default nextval('schedule_task_seq'),
--     name             VARCHAR(255),
--     status           VARCHAR(255),
--     lifecycle        jsonb,
--     metadata         jsonb,
--     create_datetime  TIMESTAMP WITH TIME ZONE,
--     update_datetime  TIMESTAMP WITH TIME ZONE
-- );
-- ALTER SEQUENCE schedule_task_seq OWNED BY schedule_task.schedule_task_id;
--
--
-- CREATE SEQUENCE task_history_seq INCREMENT by 1;
-- CREATE TABLE task_history
-- (
--     task_history_id  INTEGER PRIMARY KEY default nextval('task_history_seq'),
--     schedule_task_id INTEGER,
--     is_success       VARCHAR(255),
--     create_datetime  TIMESTAMP WITH TIME ZONE,
--     update_datetime  TIMESTAMP WITH TIME ZONE
-- );
-- ALTER SEQUENCE task_history_seq OWNED BY task_history.task_history_id;

--
-- CREATE TABLE stock
-- (
--     stock_id          VARCHAR PRIMARY KEY,
--     name              VARCHAR(255),
--     type              VARCHAR(255),
--     industry_category VARCHAR(255)
-- );
--
-- INSERT INTO account (email, password, create_datetime, update_datetime)
-- VALUES ('admin', '{bcrypt}$2a$10$Q9Z3OrHv3.SDPJn5tuyPwe2t3kPHzAa7lB4sKZjvyZinQSPsxQrOC',
--         CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));
--
-- INSERT INTO schedule_task (name, status, lifecycle, metadata, create_datetime, update_datetime)
-- VALUES ('task-1', 'Running', '{
--   "lifecycle": "Once",
--   "launchTime": {
--     "hour": 12,
--     "minute": 12
--   }
-- }'::jsonb, '{
--   "type": "Stock_Price",
--   "targetType": "All",
--   "retrieveDate": {
--     "day": 25,
--     "month": 1
--   }
-- }'::jsonb, CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));
