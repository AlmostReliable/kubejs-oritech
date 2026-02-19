# Changelog

All notable changes to this project will be documented in this file.

## Unreleased
- /

## [0.4.2] - 2026-02-19

- fixed `particleCollided` event sometimes returning incorrect collision positions when particle is too fast

## [0.4.1] - 2026-02-16

- fixed startup crash caused by class loading issues

## [0.4.0] - 2026-02-08

- added `spawnEndPortal` and `spawnNetherPortal` helper methods to particle state events
- fixed non Oritech recipes being completely removed ([#1](https://github.com/AlmostReliable/kubejs-oritech/issues/1))
- fixed duplicate recipe ID check for `deepDrillRegistration` event
- changed `deepDrillRegistration` event to use proper KubeJS exceptions to display exact source line in case of errors

## [0.3.0] - 2026-02-06

- added `particleInjected` event
  - fired when a particle is injected into the Particle Accelerator
  - can be canceled to prevent the particle from being injected
  - holds utility methods for disabling special interactions for spawning nether and end portals
- added `particleCollided` event
  - fired when a particle collides with another particle
  - can be canceled to consume the particles without an output
- added `particleExited` event
  - fired when a particle exits the Particle Accelerator
- added `deepDrillRegistration` event to handle automatic registration of the material tag and the
  recipe for the Bedrock Extractor
- renamed `position` to `pos` and `blockEntity` to `soulCollector` in `soulCollection` event

## [0.2.0] - 2026-02-06

- added `ticks`, `timeInTicks`, and `duration` aliases to assign the `time` property
- added `seconds` and `timeInSeconds` helper methods to assign the `time` property

## [0.1.0] - 2026-02-05

Initial 1.21.1 release!

<!-- Versions -->

[0.4.2]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.4.2
[0.4.1]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.4.1
[0.4.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.4.0
[0.3.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.3.0
[0.2.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.2.0
[0.1.0]: https://github.com/AlmostReliable/kubejs-oritech/releases/tag/v1.21.1-neoforge-0.1.0
