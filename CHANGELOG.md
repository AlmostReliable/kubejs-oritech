# Changelog

All notable changes to this project will be documented in this file.

## Unreleased

- added `particleInjected` event
  - fired when a Particle Accelerator injects a particle into the world
  - can be canceled to prevent the particle from being injected
  - holds utility methods for disabling special interactions for spawning nether and end portals
- added `particleCollided` event
  - fired when a particle collides with another particle
  - can be canceled to consume the particles without an output

## [0.2.0] - 2026-02-06

- added `ticks`, `timeInTicks`, and `duration` aliases to assign the `time` property
- added `seconds` and `timeInSeconds` helper methods to assign the `time` property

## [0.1.0] - 2026-02-05

Initial 1.21.1 release!

<!-- Versions -->
[0.2.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.2.0
[0.1.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.1.0
