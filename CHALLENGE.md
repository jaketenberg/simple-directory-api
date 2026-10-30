# Challenge: attribute-based access control

simple-directory-api is a multi-tenant user directory API, and not every client of the API is equal. An environment administrator manages the full user record. A limited client may create users, but some attributes of that record are read-restricted and some are write-restricted, depending on who is asking.

Add attribute-based access control to the user API: depending on who is making the request, writes that attempt to set restricted attributes must be disallowed, and reads must not return restricted attributes.

## Ground rules

- The caller's type arrives as the `X-Client-Type` header, which `ClientTypeInterceptor` requires on every request and resolves before a request reaches any controller. Assume it is trustworthy; do not add real authentication.
- The requirements deliberately do not specify which attributes are restricted, or in which direction. That is yours to decide.
- DELETE is out of scope.
- Work with your AI assistant as you would with an engineer on your team: it writes the code, you own the outcome.

## Phase 1: design, before any code

Nothing is implemented until a design is written down and approved.

Have your AI assistant read the code and propose an approach. Work through it with the same care you would give a design from a report, then write the agreed design to `DESIGN.md` in the repository root. A page is enough; phase 1 is timeboxed to about fifteen minutes. Hold the design to one bar: an engineer should be able to implement it without coming back with questions. Every behavior the requirements leave open is a decision someone has to make; better you and your AI now than the code deciding later.

Your interviewer reviews `DESIGN.md` and either approves it or redirects with specific concerns. Implementation starts only after approval.

## Phase 2: implement

Implement the approved design, with tests. If implementation surfaces something the design got wrong or did not cover, stop and raise it rather than deviating silently.
