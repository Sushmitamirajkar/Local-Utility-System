SET HEADING OFF FEEDBACK OFF VERIFY OFF TRIMSPOOL ON;

INSERT INTO services (id, name, description, category, price, location, available, provider_id)
VALUES (SERVICE_SEQ.NEXTVAL, 'House Cleaning', 'Professional house cleaning service with eco-friendly products', 'Cleaning', 500, 'Chennai, Tamil Nadu', 1, 2);

INSERT INTO services (id, name, description, category, price, location, available, provider_id)
VALUES (SERVICE_SEQ.NEXTVAL, 'Plumbing Repair', 'Expert plumbing and maintenance services for all types of fixtures', 'Plumbing', 800, 'Chennai, Tamil Nadu', 1, 2);

INSERT INTO services (id, name, description, category, price, location, available, provider_id)
VALUES (SERVICE_SEQ.NEXTVAL, 'Electrical Maintenance', 'Licensed electrician for repairs and installation', 'Electrical', 750, 'Chennai, Tamil Nadu', 1, 2);

INSERT INTO services (id, name, description, category, price, location, available, provider_id)
VALUES (SERVICE_SEQ.NEXTVAL, 'Interior Painting', 'Professional painting service with high-quality paints', 'Painting', 1200, 'Chennai, Tamil Nadu', 1, 2);

INSERT INTO services (id, name, description, category, price, location, available, provider_id)
VALUES (SERVICE_SEQ.NEXTVAL, 'Garden Landscaping', 'Design and maintenance of outdoor spaces', 'Gardening', 2000, 'Chennai, Tamil Nadu', 1, 2);

COMMIT;
EXIT;
