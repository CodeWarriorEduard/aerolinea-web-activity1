
INSERT INTO aerolineas (id, nombre) VALUES
                                        (1, 'AeroJet'),
                                        (2, 'SkyHigh Airlines'),
                                        (3, 'VuelaSeguro'),
                                        (4, 'BlueWings'),
                                        (5, 'RapidAir'),
                                        (6, 'FlyAway'),
                                        (7, 'AirNova'),
                                        (8, 'CloudAir'),
                                        (9, 'GoldenAir'),
                                        (10, 'EagleFlight');


INSERT INTO pasajeros (id, nId, nombre) VALUES
                                             (1, 'P001', 'Juan Perez'),
                                             (2, 'P002', 'Maria Lopez'),
                                             (3, 'P003', 'Carlos Gomez'),
                                             (4, 'P004', 'Ana Martinez'),
                                             (5, 'P005', 'Pedro Sanchez'),
                                             (6, 'P006', 'Luisa Torres'),
                                             (7, 'P007', 'Roberto Diaz'),
                                             (8, 'P008', 'Elena Ramirez'),
                                             (9, 'P009', 'Javier Castillo'),
                                             (10, 'P010', 'Natalia Rios');


INSERT INTO pasaportes (id, pasajero_id, numero) VALUES
                                                     (1, 1, 'X1234567'),
                                                     (2, 2, 'X2345678'),
                                                     (3, 3, 'X3456789'),
                                                     (4, 4, 'X4567890'),
                                                     (5, 5, 'X5678901'),
                                                     (6, 6, 'X6789012'),
                                                     (7, 7, 'X7890123'),
                                                     (8, 8, 'X8901234'),
                                                     (9, 9, 'X9012345'),
                                                     (10, 10, 'X0123456');


INSERT INTO vuelos (id, numero_vuelo, destino, origen) VALUES
                                                           (1, gen_random_uuid(), 'Nueva York', 'Madrid'),
                                                           (2, gen_random_uuid(), 'Londres', 'Paris'),
                                                           (3, gen_random_uuid(), 'Tokio', 'Los Angeles'),
                                                           (4, gen_random_uuid(), 'Buenos Aires', 'Santiago'),
                                                           (5, gen_random_uuid(), 'Berlin', 'Roma'),
                                                           (6, gen_random_uuid(), 'Sidney', 'Singapur'),
                                                           (7, gen_random_uuid(), 'Toronto', 'Mexico DF'),
                                                           (8, gen_random_uuid(), 'Moscú', 'Estambul'),
                                                           (9, gen_random_uuid(), 'Dubai', 'Bangkok'),
                                                           (10, gen_random_uuid(), 'Seúl', 'Hong Kong');


INSERT INTO reservas (id, pasajero_id, vuelo_id, codigo_reserva) VALUES
                                                                     (1, 1, 1, gen_random_uuid()),
                                                                     (2, 2, 2, gen_random_uuid()),
                                                                     (3, 3, 3, gen_random_uuid()),
                                                                     (4, 4, 4, gen_random_uuid()),
                                                                     (5, 5, 5, gen_random_uuid()),
                                                                     (6, 6, 6, gen_random_uuid()),
                                                                     (7, 7, 7, gen_random_uuid()),
                                                                     (8, 8, 8, gen_random_uuid()),
                                                                     (9, 9, 9, gen_random_uuid()),
                                                                     (10, 10, 10, gen_random_uuid());


INSERT INTO vuelos_aerolineas (aerolinea_id, vuelo_id) VALUES
                                                           (1, 1),
                                                           (2, 2),
                                                           (3, 3),
                                                           (4, 4),
                                                           (5, 5),
                                                           (6, 6),
                                                           (7, 7),
                                                           (8, 8),
                                                           (9, 9),
                                                           (10, 10);

