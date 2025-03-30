-- USUARIOS
CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    nombre_usuario VARCHAR(50) UNIQUE,
    avatar VARCHAR(200),
    notificaciones BOOLEAN
);

-- JUEGOS
CREATE TABLE juego (
    id_juego BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

-- RELACIÓN USUARIO-JUEGO (N:N)
CREATE TABLE usuario_juego (
    id_usuario BIGINT,
    id_juego BIGINT,
    PRIMARY KEY (id_usuario, id_juego),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_juego) REFERENCES juego(id_juego)
);

-- ORGANIZADORES
CREATE TABLE organizador (
    id_organizador BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    contacto VARCHAR(100)
);

-- EVENTOS
CREATE TABLE evento (
    id_evento BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200),
    descripcion TEXT,
    direccion VARCHAR(200),
    fecha DATE,
    hora TIME,
    categoria VARCHAR(50),
    ciudad VARCHAR(50),
    pais VARCHAR(50)
);

-- RELACIÓN EVENTO-ORGANIZADOR (N:N)
CREATE TABLE evento_organizador (
    id_evento BIGINT,
    id_organizador BIGINT,
    PRIMARY KEY (id_evento, id_organizador),
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento),
    FOREIGN KEY (id_organizador) REFERENCES organizador(id_organizador)
);

-- SERVICIOS
CREATE TABLE servicio_evento (
    id_evento BIGINT PRIMARY KEY,
    estacionamiento BOOLEAN,
    locomocion BOOLEAN,
    comidas BOOLEAN,
    alojamiento BOOLEAN,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento)
);

-- EXPOSITORES
CREATE TABLE expositor (
    id_expositor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255),
    id_evento BIGINT,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento)
);

-- ===========================================
-- INSERCIONES DE PRUEBA
-- ===========================================

-- USUARIOS
INSERT INTO usuario (nombre, correo, password, nombre_usuario, avatar, notificaciones) VALUES
('Admin', 'admin@gamer.cl', '$2a$10$QpfagbzH22p6bMC6snT8vOP2jyCo.8YIMR9/97gaZrZEFc2WnWSJO', 'admin', NULL, TRUE),
('Usuario1', 'user1@gamer.cl', '$2a$10$QpfagbzH22p6bMC6snT8vOP2jyCo.8YIMR9/97gaZrZEFc2WnWSJO', 'user1', NULL, FALSE),
('Usuario2', 'user2@gamer.cl', '$2a$10$QpfagbzH22p6bMC6snT8vOP2jyCo.8YIMR9/97gaZrZEFc2WnWSJO', 'user2', NULL, TRUE);

-- JUEGOS
INSERT INTO juego (nombre) VALUES ('League of Legends'), ('CS:GO'), ('Valorant'), ('Minecraft');

-- ASOCIACIONES USUARIO-JUEGO
INSERT INTO usuario_juego VALUES (1,1), (1,2), (2,3), (3,4);

-- ORGANIZADORES
INSERT INTO organizador (nombre, contacto) VALUES
('Team LAN Party', 'contacto@lanparty.cl'),
('Expo Retro', 'retro@expo.cl');

-- EVENTOS
INSERT INTO evento (titulo, descripcion, direccion, fecha, hora, categoria, ciudad, pais) VALUES
('GamerFest 2025', 'Evento gamer masivo con competencias y exposición.', 'Centro de Eventos XYZ', '2025-05-20', '15:00:00', 'eSports', 'Santiago', 'Chile'),
('LAN Party Santiago', 'Competencia de LAN y comunidad.', 'Parque Digital', '2025-06-10', '10:00:00', 'LAN Party', 'Santiago', 'Chile'),
('Expo RetroGames', 'Lo mejor de los juegos clásicos.', 'Galpón 9', '2025-07-05', '12:00:00', 'Retro', 'Valparaíso', 'Chile');

-- SERVICIOS
INSERT INTO servicio_evento VALUES
(1, TRUE, TRUE, TRUE, FALSE),
(2, TRUE, FALSE, TRUE, TRUE),
(3, FALSE, TRUE, TRUE, TRUE);

-- RELACIÓN EVENTO-ORGANIZADOR
INSERT INTO evento_organizador VALUES (1,1), (2,1), (3,2);

-- EXPOSITORES
INSERT INTO expositor (nombre, descripcion, id_evento) VALUES
('Nintendo', 'Stand con novedades', 1),
('HyperX', 'Stand con periféricos', 2),
('RetroGamer Chile', 'Stand retro', 3);