
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


INSERT INTO pasajeros (id, n_id, nombre) VALUES
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
                                                                     (1, 1, 1, '550e8400-e29b-41d4-a716-446655440001'),
                                                                     (2, 2, 2, '550e8400-e29b-41d4-a716-446655440002'),
                                                                     (3, 3, 3, '550e8400-e29b-41d4-a716-446655440003'),
                                                                     (4, 4, 4, '550e8400-e29b-41d4-a716-446655440004'),
                                                                     (5, 5, 5, '550e8400-e29b-41d4-a716-446655440005'),
                                                                     (6, 6, 6, '550e8400-e29b-41d4-a716-446655440006'),
                                                                     (7, 7, 7, '550e8400-e29b-41d4-a716-446655440007'),
                                                                     (8, 8, 8, '550e8400-e29b-41d4-a716-446655440008'),
                                                                     (9, 9, 9, '550e8400-e29b-41d4-a716-446655440009'),
                                                                     (10, 10, 10, '550e8400-e29b-41d4-a716-446655440010');



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



INSERT INTO vuelos_aerolineas (aerolinea_id, vuelo_id) VALUES
                                                           (1, 11),  -- AeroJet
                                                           (2, 11);  -- SkyHigh Airlines