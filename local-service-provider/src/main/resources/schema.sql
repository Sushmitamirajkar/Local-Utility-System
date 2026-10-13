-- ============================================
-- LOCAL SERVICE PROVIDER - ORACLE SQL SCHEMA
-- ============================================

-- Sequences
CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE SERVICE_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE BOOKING_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE REVIEW_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- Users Table
CREATE TABLE users (
    id        NUMBER PRIMARY KEY,
    name      VARCHAR2(100) NOT NULL,
    email     VARCHAR2(150) UNIQUE NOT NULL,
    password  VARCHAR2(255) NOT NULL,
    phone     VARCHAR2(20),
    address   VARCHAR2(255),
    role      VARCHAR2(20) NOT NULL CHECK (role IN ('USER', 'PROVIDER', 'ADMIN')),
    active    NUMBER(1) DEFAULT 1 NOT NULL
);

-- Services Table
CREATE TABLE services (
    id          NUMBER PRIMARY KEY,
    name        VARCHAR2(150) NOT NULL,
    description VARCHAR2(500),
    category    VARCHAR2(100),
    price       NUMBER(10,2),
    location    VARCHAR2(200),
    available   NUMBER(1) DEFAULT 1 NOT NULL,
    provider_id NUMBER NOT NULL,
    CONSTRAINT fk_service_provider FOREIGN KEY (provider_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Bookings Table
CREATE TABLE bookings (
    id           NUMBER PRIMARY KEY,
    user_id      NUMBER NOT NULL,
    service_id   NUMBER NOT NULL,
    booking_date TIMESTAMP NOT NULL,
    notes        VARCHAR2(500),
    status       VARCHAR2(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING','ACCEPTED','REJECTED','COMPLETED','CANCELLED')),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_booking_user    FOREIGN KEY (user_id)    REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_booking_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
);

-- Reviews Table
CREATE TABLE reviews (
    id         NUMBER PRIMARY KEY,
    user_id    NUMBER NOT NULL,
    service_id NUMBER NOT NULL,
    rating     NUMBER(1) NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment    VARCHAR2(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_user    FOREIGN KEY (user_id)    REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_review_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
);

-- ============================================
-- SEED DATA
-- ============================================

-- Admin user (password: Sanjay@8792 - BCrypt encoded)
INSERT INTO users (id, name, email, password, phone, address, role, active)
VALUES (USER_SEQ.NEXTVAL, 'Sanjay', 'sanjaymuddebihal99@company.com',
        '$2a$10$wp4AOg49i85GRyEF578KWOfrC0Sit0NQuNLiuPn4.0/CGedisJf76',
        '9999999999', 'Admin Office', 'ADMIN', 1);

-- Sample Provider (password: provider123)
INSERT INTO users (id, name, email, password, phone, address, role, active)
VALUES (USER_SEQ.NEXTVAL, 'Ravi Kumar', 'ravi@provider.com',
        '$2a$10$8K1p/a0dR1xqM2LtPgYnVOGys.Lx1QZs4K1p/a0dR1xqM2LtPgYnV',
        '9876543210', 'Chennai, Tamil Nadu', 'PROVIDER', 1);

-- Sample User (password: user123)
INSERT INTO users (id, name, email, password, phone, address, role, active)
VALUES (USER_SEQ.NEXTVAL, 'Priya Sharma', 'priya@user.com',
        '$2a$10$8K1p/a0dR1xqM2LtPgYnVOGys.Lx1QZs4K1p/a0dR1xqM2LtPgYnV',
        '9123456789', 'Mumbai, Maharashtra', 'USER', 1);

-- Sample Services from Ravi Kumar (provider)
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
