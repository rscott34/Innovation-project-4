## TC-TL-01: Traceability timeline shows exactly 5 stages

## TC-TL-01: Traceability timeline shows exactly 5 stages

Status: Planned (not yet executed)

Test Objective:
Verify that the traceability timeline accurately represents the full
end-to-end supply chain in a clear and correct order, in line with
CW1 success criteria.

Assumptions:
- Product data has been seeded correctly
- Product contains a complete traceability history
- User has access to view the product page

Feature: Traceability Timeline
Precondition: Product exists in dataset with full timeline

Steps:
1. Enter valid ProductID
2. Navigate to product page
3. Observe traceability timeline

Pass Criteria:
- Exactly 5 stages are displayed
- Stages appear in the correct order:
  Raw → Processing → Assembly → Transport → Retail
- No stages missing or duplicated

Fail Criteria:
- Fewer or more than 5 stages displayed
- Incorrect ordering of stages
- Duplicate or missing stages

- Actual Result:
TBD (pending implementation)

Evidence:
To be captured after execution (screenshots)
