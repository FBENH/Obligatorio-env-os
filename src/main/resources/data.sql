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
            ('Admin'), 
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
            ('carlos789', '$2a$10$1rEWITvNv6bKTBqjWcALeeLwISi52bL57RDOd4Pj8132mC0OhMWW2', 'carlos@example.com', b'1');

        -- Insertar datos en la tabla clientes (incluir todas las columnas)
        INSERT INTO clientes (cedula, domicilio, telefono, nombre_usuario) VALUES 
            ('12345678', 'Calle Falsa 123', '099123456', 'juan123'), 
            ('87654321', 'Av. Siempre Viva 742', '099654321', 'ana456');

        -- Insertar datos en la tabla empleados (incluir todas las columnas)
        INSERT INTO empleados (nombre_usuario, sucursal_numero) VALUES 
            ('carlos789', 101);

        -- Insertar datos en la tabla paquetes (incluir todas las columnas)
        INSERT INTO paquetes (id, cobroadestinatario, direccion_destinatario, fecha_hora, nombre_destinatario, telefono_destinatario, categoria_id, estado_rastreo_id) VALUES 
            (1, b'0', 'Calle Luna 12', '2024-10-01 12:34:56', 'Pedro Pérez', '099111222', 1, 1), 
            (2, b'1', 'Calle Sol 34', '2024-10-02 13:45:56', 'Luis López', '099333444', 2, 2);

        -- Insertar datos en la tabla clientes_paquetes (incluir todas las columnas)
        INSERT INTO clientes_paquetes (cliente_nombre_usuario, paquetes_id) VALUES 
            ('juan123', 1), 
            ('ana456', 2);

        -- Insertar datos en la tabla usuarios_roles (incluir todas las columnas)
        INSERT INTO usuarios_roles (usuarios_roles, rol_nombre_rol) VALUES 
            ('juan123', 'Cliente'), 
            ('ana456', 'Cliente'), 
            ('carlos789', 'Empleado');
    END IF;
END^;

CALL cargar_datos_iniciales()^;
