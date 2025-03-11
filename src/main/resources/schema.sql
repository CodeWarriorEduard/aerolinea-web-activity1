
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


INSERT INTO pasajeros (id, nid, nombre) VALUES
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
                                                           (1, '8a10a6d9-5b1b-47fd-8718-240441d6430e', 'Nueva York', 'Madrid'),
                                                           (2, 'c8b6762f-3082-43af-9e2a-75b3d21d0d8c', 'Londres', 'Paris'),
                                                           (3, '133c036a-4b19-4e6f-8047-c00911d4714d', 'Tokio', 'Los Angeles'),
                                                           (4, 'c617102a-9d57-41e5-bc2b-3729b009111b', 'Buenos Aires', 'Santiago'),
                                                           (5, '06cbdb78-4a18-4a70-a841-14b98be92d33', 'Berlin', 'Roma'),
                                                           (6, '6aee7797-8099-4c41-8da1-27f7e9703b9c', 'Sidney', 'Singapur'),
                                                           (7, '450e8f5e-bc50-4562-a6d2-e7a3236ff7e7', 'Toronto', 'Mexico DF'),
                                                           (8, 'adf6cf0e-8cbe-4fc6-8fbc-5a0c5e195925', 'Moscú', 'Estambul'),
                                                           (9, '85cddd22-ebfa-4e6f-bc5a-89b85e45f1f8', 'Dubai', 'Bangkok'),
                                                           (10, '01e8c9dd-9fb6-416a-9090-c54a3f9ac747', 'Seúl', 'Hong Kong'),
                                                           (11, '114e19e3-aec1-423d-b53f-3525a106b450', 'Miami', 'Los Angeles');



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



-- Asociar el nuevo vuelo a dos aerolíneas (ejemplo: AeroJet y SkyHigh Airlines)
INSERT INTO vuelos_aerolineas (aerolinea_id, vuelo_id)
VALUES
    (1, 11),  -- AeroJet
    (2, 11);  -- SkyHigh Airlines