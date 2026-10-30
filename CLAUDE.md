# Working agreements

simple-directory-api is a small Spring Boot REST service. Main code is in `src/main/java/com/ping/simpledirectoryapi` and tests are in `src/test/java/com/ping/simpledirectoryapi`. Build and test with `./mvnw test`; no external services are required.

## Design before code

For any change to application behavior, do not start by writing code.

1. Read the affected code until you can describe how a request actually flows through it: interceptors, controller, service, store.
2. Propose a design in conversation. Say what changes and in which classes. At every fork in the road, present the options with the trade-off of each in a sentence. Surface anything ambiguous as an explicit question instead of resolving it silently.
3. Wait for the user's explicit approval before writing any code. Discussing the design is not approval to implement it.

If the user asks a question about the code or the problem space, answer the question. Do not treat it as a request to start implementing.

## While implementing

- Implement what the approved design says, and no more. If the code turns out not to support part of the design, such as a wrong assumption, a test that cannot pass, or an edge case the design does not cover, stop and raise it before deviating.
- If you see a consequence of the design that was not discussed, for example an endpoint or caller the design affects that nobody mentioned, say so before or as you implement it, not afterward.
- When the user delegates a decision to you ("your call"), pick one option, state it and its reason in a sentence, and continue.
- If the user asks you to skip the design step, state in a sentence what that risks, then follow their direction.
- Run `./mvnw test` before reporting the work as done, and report the result as it actually came out.

## Style

Match the existing code: plain Java, public fields, small classes, and no frameworks or layers the problem does not need. Tests live next to the existing ones and follow their patterns.
