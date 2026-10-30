# Challenge: attribute-based access control

simple-directory-api is a multi-tenant user directory API, and not every client of the API is equal. An environment administrator manages the full user record. A limited client may create users, but some attributes of that record are read-restricted and some are write-restricted, depending on who is asking.

Add attribute-based access control to the user API: depending on who is making the request, writes that attempt to set restricted attributes must be disallowed, and reads must not return restricted attributes.

## Ground rules

- The caller's type arrives as the `X-Client-Type` header (`admin` or `limited`), which `ClientTypeInterceptor` resolves before a request reaches any controller. Assume it is trustworthy; do not add real authentication.
- The requirements do not say which attributes are restricted, or in which direction. Deciding that is part of the design.
- DELETE is out of scope.
- Work with your AI assistant as you would with an engineer on your team: it writes the code, you own the outcome.

## Phase 1: design, before any code

Nothing is implemented until a design is written down and approved.

Have your AI assistant read the code and propose an approach. Work through it with the same care you would give a design from a report, then write the agreed design to `DESIGN.md` in the repository root. A page is enough. Phase 1 is timeboxed to about fifteen minutes, so favor short, reasoned paragraphs over exhaustive analysis. The design must resolve each of these with your reasoning, not just name a choice:

1. **Restricted attributes.** Which attributes are read-restricted or write-restricted for a limited client, and why. Ground the choice in what the data is, not in convenience.
2. **Enforcement point.** Which layer of the service enforces the restrictions, and why that layer rather than the others.
3. **Caller identity.** `ClientTypeInterceptor` leaves the client type as a request attribute that nothing downstream reads today. How does the client type reach the code that enforces the policy, and why that way?
4. **Reject or drop.** When a limited client sends a write that sets a restricted attribute: reject the whole request, ignore the attribute and process the rest, or something else? Include the HTTP contract: status code and error body.
5. **Read scope.** Which responses must be filtered for a limited client. A GET is a read. Decide whether the bodies of POST and PATCH responses are reads too, and what a limited client sees in each response.
6. **PATCH with mixed fields.** A limited client sends a PATCH that changes an allowed attribute and a restricted attribute in the same request. What happens, exactly?
7. **The next client type.** A third client type ships next quarter with its own restrictions on a couple of attributes. What changes in your design, and what does not?

Your interviewer reviews `DESIGN.md` and either approves it or redirects with specific concerns. Implementation starts only after approval.

## Phase 2: implement

Implement the approved design, with tests. If implementation surfaces something the design got wrong or did not cover, stop and raise it rather than deviating silently.
