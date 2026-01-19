# QA Test Strategy (v0.1)

## 1. Purpose of QA
The purpose of quality assurance in this project is to ensure that the
traceability system presents accurate, consistent, and trustworthy
information to users. QA activities focus on validating traceability
data, origin breakdown calculations, claim and evidence linking, and
quest mission behaviour, in line with coursework requirements.

## 2. Quality Risks
The following quality risks have been identified for this project:
- Origin breakdown percentages not summing to 100%
- Claims marked as “verified” without supporting evidence
- Incorrect grading or feedback in quest missions
- Consumers accessing verifier-only functionality
- Seeded dataset inconsistencies affecting demonstrations
- Missing or misleading evidence links

## 3. QA Scope

### In Scope
- Product lookup by identifier
- Traceability timeline rendering
- Origin breakdown calculations
- Claim cards and evidence view
- Quest mission grading and feedback
- Verifier editing workflow
- Issue reporting workflow

### Out of Scope
- Performance and load testing
- Real-world brand verification
- External API availability

## 4. Testing Approach

### Manual Testing
Manual testing will be used to validate end-to-end user journeys,
including happy paths, failure cases, and edge-case behaviour. Manual
tests will also assess UI clarity and error handling.

### Automated Testing
Automated tests will focus on core logic and regression prevention,
including calculation correctness, quest grading, and role-based
access control.

### Data Integrity Testing
Specific tests will validate data integrity rules such as percentage
totals, evidence-link enforcement, and prevention of orphaned data.

## 5. Bug Severity and Triage

| Severity | Description |
|--------|-------------|
| Critical | Incorrect or misleading traceability data |
| High | Core feature not functioning |
| Medium | Confusing behaviour or UI issues |
| Low | Cosmetic or minor issues |

Bug lifecycle:
Open → Confirmed → In Progress → Fixed → Verified → Closed
