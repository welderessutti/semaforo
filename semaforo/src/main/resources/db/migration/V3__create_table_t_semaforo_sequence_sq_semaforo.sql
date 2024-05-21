CREATE SEQUENCE sq_semaforo
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE t_semaforo
(
    id             INTEGER DEFAULT sq_semaforo.NEXTVAL NOT NULL,
    loc_instalacao VARCHAR2(100) NOT NULL,
    tmp_verde      INTEGER                             NOT NULL,
    tmp_amarelo    INTEGER                             NOT NULL,
    tmp_vermelho   INTEGER                             NOT NULL
);
