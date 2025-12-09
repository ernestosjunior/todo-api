CREATE OR REPLACE FUNCTION fn_auditoria_todos()
RETURNS TRIGGER AS $$
BEGIN
    -- Inserção
    IF TG_OP = 'INSERT' THEN
        INSERT INTO todos_auditoria (
            operation,
            operation_time,
            user_name,
            todo_id,
            title,
            description,
            priority,
            status,
            deadline,
            user_id
        )
        VALUES (
            'I',            
            NOW(),            
            current_user,        
            NEW.id,            
            NEW.title,
            NEW.description,
            NEW.priority,
            NEW.status,
            NEW.deadline,
            NEW.user_id
        );
        RETURN NEW;
    END IF;

    -- Atualização
    IF TG_OP = 'UPDATE' THEN
        INSERT INTO todos_auditoria (
            operation,
            operation_time,
            user_name,
            todo_id,
            title,
            description,
            priority,
            status,
            deadline,
            user_id
        )
        VALUES (
            'U', 
            NOW(), 
            current_user,
            OLD.id,
            OLD.title,
            OLD.description,
            OLD.priority,
            OLD.status,
            OLD.deadline,
            OLD.user_id
        );
        RETURN NEW;
    END IF;

    -- Deleção
    IF TG_OP = 'DELETE' THEN
        INSERT INTO todos_auditoria (
            operation,
            operation_time,
            user_name,
            todo_id,
            title,
            description,
            priority,
            status,
            deadline,
            user_id
        )
        VALUES (
            'D', 
            NOW(), 
            current_user,
            OLD.id,
            OLD.title,
            OLD.description,
            OLD.priority,
            OLD.status,
            OLD.deadline,
            OLD.user_id
        );
        RETURN OLD;
    END IF;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_auditoria_todos ON todos;

CREATE TRIGGER trg_auditoria_todos
AFTER INSERT OR UPDATE OR DELETE
ON todos
FOR EACH ROW
EXECUTE FUNCTION fn_auditoria_todos();