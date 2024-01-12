create table productos
(
    id                    bigint auto_increment comment 'Id del la tabla.',
    modelo                varchar(10) not null comment 'Modelo del Producto',
    año_salida            varchar(30) null comment 'Año del modelo',
    ram                   varchar(30) null comment 'memoria asociada al modelo',
    usuario_creacion      varchar(50)                         null comment 'Columna de Auditoria',
    usuario_modificacion  varchar(50)                         null comment 'Columna de Auditoria',
    fecha_creacion        timestamp default CURRENT_TIMESTAMP null comment 'Columna de Auditoria',
    fecha_modificacion    timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Columna de Auditoria',
    primary key (id)
);

create table detalles
(
    id                     bigint auto_increment comment 'Id del la tabla.',
    productos_id            bigint not null comment 'Id del Producto asociado',
    almacenamiento         varchar(50)                         not null comment 'Almacenamiento del Producto asociado',
    gpu                    varchar(50)                         not null comment 'Gpu del Producto asociado',
    usuario_creacion       varchar(50)                         null comment 'Columna de Auditoria',
    usuario_modificacion   varchar(50)                         null comment 'Columna de Auditoria',
    fecha_creacion         timestamp default CURRENT_TIMESTAMP null comment 'Columna de Auditoria',
    fecha_modificacion     timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Columna de Auditoria',
    primary key (id),
    foreign key (productos_id) references productos(id)
);

