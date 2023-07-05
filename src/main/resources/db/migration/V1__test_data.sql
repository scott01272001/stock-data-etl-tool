CREATE SEQUENCE schedule_task_seq INCREMENT by 1;
CREATE TABLE schedule_task
(
    schedule_task_id INTEGER PRIMARY KEY default nextval('schedule_task_seq'),
    task_name        VARCHAR(255),
    task_status      VARCHAR(255),
    create_datetime  TIMESTAMP WITH TIME ZONE,
    update_datetime  TIMESTAMP WITH TIME ZONE
);
ALTER SEQUENCE schedule_task_seq OWNED BY schedule_task.schedule_task_id;


CREATE SEQUENCE task_history_seq INCREMENT by 1;
CREATE TABLE task_history
(
    task_history_id  INTEGER PRIMARY KEY default nextval('task_history_seq'),
    schedule_task_id INTEGER,
    is_success       VARCHAR(255),
    create_datetime  TIMESTAMP WITH TIME ZONE,
    update_datetime  TIMESTAMP WITH TIME ZONE
);
ALTER SEQUENCE task_history_seq OWNED BY task_history.task_history_id;


CREATE TABLE stock
(
    stock_id  INTEGER PRIMARY KEY,
    name      VARCHAR(255)
);


INSERT INTO schedule_task (task_name, task_status, create_datetime, update_datetime)
VALUES ('task-1', 'Running', CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));

INSERT INTO schedule_task (task_name, task_status, create_datetime, update_datetime)
VALUES ('task-2', 'Stop', CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));
