CREATE PROCEDURE IF NOT EXISTS cargar_datos_iniciales()
BEGIN
    IF NOT EXISTS (SELECT * FROM categorias)
    THEN
        -- Insertar datos en la tabla estados_rastreo
        INSERT INTO estados_rastreo (id, descripcion) VALUES 
            (1, 'En tránsito'), 
            (2, 'Entregado'), 
            (3, 'Pendiente');

        -- Insertar datos en la tabla categorias
        INSERT INTO categorias (id, nombre) VALUES 
            (1, 'Electrónica'), 
            (2, 'Ropa'), 
            (3, 'Juguetes');   

        -- Insertar datos en la tabla roles
        INSERT INTO roles (nombre_rol) VALUES             
            ('Cliente'), 
            ('Empleado');

        -- Insertar datos en la tabla sucursales
        INSERT INTO sucursales (numero, nombre) VALUES 
            (101, 'Sucursal Central'), 
            (102, 'Sucursal Norte'), 
            (103, 'Sucursal Sur');

        -- Insertar datos en la tabla usuarios (incluir todas las columnas)
        INSERT INTO usuarios (nombre_usuario, clave, correo, activo) VALUES 
            ('juan123', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'juan@example.com', b'1'), 
            ('ana456', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'ana@example.com', b'1'), 
            ('pedro123', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'pedro@example.com', b'1'), 
            ('jorge123', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'jorge@example.com', b'1'), 
            ('carlos789', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'carlos@example.com', b'1'),
            ('empleado1', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado1@example.com', b'1'),
            ('empleado2', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado2@example.com', b'1'),
            ('empleado3', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado3@example.com', b'1'),
            ('empleado4', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado4@example.com', b'1'),
            ('empleado5', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado5@example.com', b'1'),
            ('empleado6', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado6@example.com', b'1'),
            ('empleado7', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado7@example.com', b'1'),
            ('empleado8', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado8@example.com', b'1'),
            ('empleado9', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado9@example.com', b'1'),
            ('empleado10', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado10@example.com', b'1'),
            ('empleado11', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado11@example.com', b'1'),
            ('empleado12', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado12@example.com', b'1'),
            ('empleado13', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado13@example.com', b'1'),
            ('empleado14', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado14@example.com', b'1'),
            ('empleado15', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado15@example.com', b'1'),
            ('empleado16', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado16@example.com', b'1'),
            ('empleado17', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado17@example.com', b'1'),
            ('empleado18', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado18@example.com', b'1'),
            ('empleado19', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'empleado19@example.com', b'1');

        -- Insertar datos en la tabla clientes (incluir todas las columnas)
        INSERT INTO clientes (cedula, domicilio, telefono, nombre_usuario) VALUES 
            ('12345678', 'Calle Falsa 123', '099123456', 'juan123'), 
            ('12345678', 'Calle Falsa 123', '099123456', 'pedro123'), 
            ('12345678', 'Calle Falsa 123', '099123456', 'jorge123'), 
            ('87654321', 'Av. Siempre Viva 742', '099654321', 'ana456');

        -- Insertar datos en la tabla empleados (incluir todas las columnas)
        INSERT INTO empleados (nombre_usuario, sucursal_numero) VALUES 
            ('carlos789', 101),
            ('empleado1', 102),
            ('empleado2', 103),
            ('empleado3', 102),
            ('empleado4', 101),
            ('empleado5', 102),
            ('empleado6', 103),
            ('empleado7', 102),
            ('empleado8', 101),
            ('empleado9', 102),
            ('empleado10', 103),
            ('empleado11', 102),
            ('empleado12', 101),
            ('empleado13', 102),
            ('empleado14', 103),
            ('empleado15', 102),
            ('empleado16', 101),
            ('empleado17', 102),
            ('empleado18', 103),
            ('empleado19', 101);

        -- Insertar datos en la tabla paquetes (incluir todas las columnas)
        INSERT INTO paquetes (id, cobroadestinatario, direccion_destinatario, fecha_hora, nombre_destinatario, telefono_destinatario, categoria_id, estado_rastreo_id, nombre_usuario) VALUES 
            (1, b'0', 'Calle Luna 12', '2024-10-01 12:34:56', 'Pedro Pérez', '099111222', 1, 1, 'juan123'), 
            (2, b'1', 'Calle Sol 34', '2024-10-02 13:45:56', 'Luis López', '099333444', 2, 2, 'ana456');

        -- Insertar datos en la tabla clientes_paquetes (incluir todas las columnas)
        /* INSERT INTO clientes_paquetes (cliente_nombre_usuario, paquetes_id) VALUES 
            ('juan123', 1), 
            ('ana456', 2); */

        -- Insertar datos en la tabla usuarios_roles (incluir todas las columnas)
        INSERT INTO usuarios_roles (usuarios_roles, rol_nombre_rol) VALUES 
            ('juan123', 'Cliente'), 
            ('ana456', 'Cliente'), 
            ('pedro123', 'Cliente'), 
            ('jorge123', 'Cliente'), 
            ('carlos789', 'Empleado'),
            ('empleado1', 'Empleado'),
            ('empleado2', 'Empleado'),
            ('empleado3', 'Empleado'),
            ('empleado4', 'Empleado'),
            ('empleado5', 'Empleado'),
            ('empleado6', 'Empleado'),
            ('empleado7', 'Empleado'),
            ('empleado8', 'Empleado'),
            ('empleado9', 'Empleado'),
            ('empleado10', 'Empleado'),
            ('empleado11', 'Empleado'),
            ('empleado12', 'Empleado'),
            ('empleado13', 'Empleado'),
            ('empleado14', 'Empleado'),
            ('empleado15', 'Empleado'),
            ('empleado16', 'Empleado'),
            ('empleado17', 'Empleado'),
            ('empleado18', 'Empleado'),
            ('empleado19', 'Empleado');
    END IF;
END^;

CALL cargar_datos_iniciales()^;
