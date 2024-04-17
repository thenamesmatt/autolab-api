CREATE TABLE audit_type (
    audit_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE audits (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    audit_type_id INT NOT NULL,
    performed_by INT NOT NULL,
    performed_on DATETIME NOT NULL,
    details TEXT,
    FOREIGN KEY (audit_type_id) REFERENCES audit_type(audit_type_id)
);