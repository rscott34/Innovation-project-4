---
name: task.yml
about: Describe this issue template's purpose here.
title: ''
labels: ''
assignees: ''

---

name: Task
description: Sprint task for Traceability Quest project
title: "[Task] <short description>"
labels: ["task"]
body:
  - type: dropdown
    id: role
    attributes:
      label: Primary Role Responsible
      options:
        - Project Lead
        - Technical Lead
        - Software Developer
        - Data Lead
        - UI/UX Designer
        - QA & Testing
        - DevOps
        - Documentation & Comms
    validations:
      required: true

  - type: textarea
    id: task_description
    attributes:
      label: Task Description
      description: What needs to be done?
      value: |
        Describe the task clearly and concisely.
        Reference relevant requirements or sprint goals.
    validations:
      required: true

  - type: textarea
    id: role_expectations
    attributes:
      label: Role-Specific Expectations
      value: |
        Project Lead: planning, coordination, risk tracking
        Technical Lead: architecture and integration
        Developer: feature implementation and tests
        Data Lead: dataset design and validation
        UI/UX: user flows and accessibility
        QA: test cases and defect tracking
        DevOps: CI/CD and deployment
        Docs: reports and demo narrative

  - type: textarea
    id: acceptance_criteria
    attributes:
      label: Acceptance Criteria
      value: |
        - [ ] Meets user story requirements
        - [ ] Works with seeded data
        - [ ] Reviewed by another team member
    validations:
      required: true

  - type: dropdown
    id: sprint
    attributes:
      label: Sprint
      options:
        - Sprint 1
        - Sprint 2
    validations:
      required: true

  - type: dropdown
    id: priority
    attributes:
      label: Priority (MoSCoW)
      options:
        - Must
        - Should
        - Could
