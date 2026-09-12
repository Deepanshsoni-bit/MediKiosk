# MediKiosk Backend — Step 1: Patient vertical slice

This is the first vertical slice: `Patient` + `Gender` wired end-to-end
(entity → repository → service → controller), with H2 in-memory DB,
validation, clean error responses, and CORS/security scaffolding.
Everything else (Visit, ClinicalHistoryIntake, MedicalDocument, voice, OCR,
ABDM) builds on this pattern next.

## Run it

```bash
mvn spring-boot:run
```

App starts on `http://localhost:8080`.
H2 console (to eyeball the data): `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:medikiosk`
  - User: `sa`, no password

## Test it in Postman

**Create a patient**
`POST http://localhost:8080/api/patients`
```json
{
  "fullName": "Asha Patel",
  "gender": "FEMALE",
  "dateOfBirth": "1990-04-12",
  "mobileNumber": "9876543210",
  "address": "Indore, MP"
}
```
→ `201 Created` with the saved patient (including generated `id`).

**Get by id**
`GET http://localhost:8080/api/patients/1`

**Get by mobile number** (used by the "login via phone" kiosk flow)
`GET http://localhost:8080/api/patients/mobile/9876543210`

**List all**
`GET http://localhost:8080/api/patients`

**Update**
`PUT http://localhost:8080/api/patients/1` — same body shape as create.

**Delete**
`DELETE http://localhost:8080/api/patients/1`

**Validation check** — POST with `fullName` missing or `gender` missing
should return `400` with a `fieldErrors` map, not a stack trace.

**Duplicate mobile check** — create the same mobile number twice, second
should return `409` with a clear message.

## What's intentionally not here yet

- **Security is wide open** (`permitAll`) so you're not fighting auth while
  building the slice. `SecurityConfig` has a TODO marking where a JWT filter
  goes in for staff/doctor-only endpoints once we build the dashboard.
- **No ABHA/Aadhaar flow yet** — `abhaNumber`/`abhaAddress` fields exist on
  `Patient` so the shape is ready, but the actual ABDM sandbox calls (OTP
  request, enrollment) are a separate service to build next, not part of
  this slice.
- **CORS origin** is read from `app.cors.allowed-origin` in
  `application.properties`, currently `http://localhost:5173` (Vite
  default). Update it if the frontend runs elsewhere.

## Next steps (in order)

1. `Visit` entity + repo + service + controller (same pattern as Patient)
2. `ClinicalHistoryIntake` entity — this is where the voice transcript and
   later the Ayush-SOCRATES/tridoshic fields will live
3. `MedicalDocument` entity for OCR results
4. `service.voice` package: MediaRecorder endpoint → Bhashini ASR proxy
5. `service.ocr` package: Google Vision proxy
6. ABDM sandbox client (OTP request, Aadhaar enrollment, ABHA lookup)
7. JWT auth for the Doctor dashboard only
