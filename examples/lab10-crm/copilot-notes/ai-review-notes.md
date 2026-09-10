# AI review notes — Lab 10

## lab10-001 — weak vs strong (entity)
- Date: 2026-09-10
- Weak prompt used: check comments in scratch_1.java file
- Output summary: very basic class, in scratch_1.java
- Strong prompt used: check comments in Scratch.java
- Output summary: Much more complex
- Decision: accept / reject / partial
- Reason (1 sentence): I accept strong because it's got a lot more structure and code

## lab10-002 — weak vs strong (addCustomer)
- Date: 2026-09-10
- Decision: accept / reject / partial
- Reason: Again strong, since it actually wrote a customer service function, unlike weak that just made a new customer

## lab10-003 — CustomerStatus / Customer scaffold
- Rejected JPA? yes / no
- Notes: There was no JPA, and it did it almost all correct. However, I think the equals function doesn't work - mainly this line:  if (o == null || getClass() != o.getClass()) return false;

## lab10-004 — CustomerService review
- Notes: So the first thing I noticed is it does .trim().isEmpty() instead of isBlank(). I looked this up and both are functionally the same, isBlank() would be the correct one to use since this project is Java 21. It does the findByCustomerId somewhat better, since it checks if the id is null or blank. UpdateStatus is the exact same.
