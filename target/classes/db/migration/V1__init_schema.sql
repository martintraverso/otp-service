CREATE TABLE otp_tokens (
    guid UUID PRIMARY KEY,
    data JSONB NOT NULL
);

CREATE INDEX idx_otp_tokens_guid ON otp_tokens(guid);
