## TC-TL-01: Traceability timeline shows exactly 5 stages

Status: Planned

Feature:
Traceability Timeline (FR2)

Test Objective:
Verify that the product traceability timeline displays the complete
end-to-end supply chain in the correct order.

Preconditions:
- Product exists in the dataset
- Product contains full traceability data

Assumptions:
- Timeline stages are derived from product data
- User has access to the product page

Steps:
1. Enter a valid ProductID
2. Navigate to the product page
3. Observe the traceability timeline

Expected Result:
- Timeline displays exactly 5 stages:
  Raw → Processing → Assembly → Transport → Retail
- Stages appear in the correct order
- No stages are missing or duplicated

Pass Criteria:
- All 5 stages are displayed in the correct order

Fail Criteria:
- Fewer or more than 5 stages displayed
- Incorrect ordering
- Missing or duplicate stages

Actual Result:
TBD 

Evidence:
To be captured after execution

---

## TC-SS-01: User can access product page via ProductID lookup

Status: Planned

Feature: Single-Scan Lookup (FR1)

Test Objective:
Verify that a user can enter a valid ProductID and successfully navigate
to the corresponding product page displaying relevant product information.

Preconditions:
- Product exists in the dataset
- Product has a valid ProductID
- Application is running and accessible

Assumptions:
- Product lookup uses a seeded local dataset (JSON / SQL)
- User does not need to be authenticated
- Product page loads automatically after lookup

Steps:
1. Navigate to the application homepage
2. Enter a valid ProductID into the input field
3. Submit the ProductID (via button or equivalent action)
4. Observe system response

Expected Result:
- System locates the product associated with the entered ProductID
- User is navigated to the correct product page
- Product page displays core product information (e.g. name, category)

Pass Criteria:
- Correct product page loads successfully for a valid ProductID

Fail Criteria:
- Product page does not load
- Incorrect product is displayed
- Error occurs for a valid ProductID

Actual Result:
TBD

Evidence:
To be captured after execution

---

## TC-CL-01: Evidence-backed claim card displays linked evidence

Status: Planned

Feature: Evidence-Backed Claims (FR3)

Test Objective:
Verify that at least one claim card (e.g. "Organic") is displayed on the
product page and allows the user to view linked evidence supporting the claim.

Preconditions:
- Product exists in the dataset
- Product contains at least one claim with linked evidence
- Evidence file or summary (e.g. PDF, certificate text) is available

Assumptions:
- Claims are derived from product data
- Evidence is linked to claims via the dataset
- Evidence may open in a modal, new page, or downloadable file

Steps:
1. Enter a valid ProductID
2. Navigate to the product page
3. Locate a claim card (e.g. "Organic")
4. Click on the claim card or associated evidence link
5. Observe system response

Expected Result:
- At least one claim card is visible on the product page
- Claim displays a confidence label (e.g. Verified / Partially Verified)
- Clicking the claim opens linked evidence
- Evidence content is readable and relevant to the claim

Pass Criteria:
- Claim card is displayed
- Linked evidence opens successfully
- Evidence corresponds to the selected claim

Fail Criteria:
- No claim card displayed
- Evidence link does not work
- Evidence is missing or unrelated to the claim

Actual Result:
TBD

Evidence:
To be captured after execution

---

## TC-E2E-01: CW1 vertical slice end-to-end user journey

Status: Planned

Feature: CW1 Consumer Vertical Slice

Test Objective:
Verify that a consumer can complete the full CW1-required journey for a
single product, from lookup through traceability, evidence review, and
completion of one mission with progress update.

Preconditions:
- Product exists in the dataset
- Product contains full traceability data (5 stages)
- Product includes at least one evidence-backed claim
- At least one mission is available for the product

Assumptions:
- User is not required to log in
- Mission answers are automatically evaluated
- Progress updates immediately after mission completion

Steps:
1. Enter a valid ProductID
2. Navigate to the product page
3. Verify the 5-stage traceability timeline is displayed
4. Locate and open a claim card with linked evidence
5. Start one mission related to the product
6. Select an answer and submit
7. Observe feedback and progress update

Expected Result:
- Product page loads successfully
- Traceability timeline displays 5 stages in correct order
- Claim card opens linked evidence
- Mission can be completed
- User receives feedback (correct/incorrect)
- User progress is updated (e.g. score increases)

Pass Criteria:
- All steps complete without error
- User successfully completes one mission
- Progress update is visible

Fail Criteria:
- Any step fails or does not load
- Mission cannot be completed
- Progress does not update

Actual Result:
TBD

Evidence:
Screenshots or screen recording covering the full user journey
