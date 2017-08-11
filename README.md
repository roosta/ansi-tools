# Nansi

Simple node application that does various things with ascii/ansi art.
Currently under development so things change dramatically everything I update it.

## Installation

```shell
git clone https://github.com/roosta/nansi && lein cljsbuild once
```
## Usage
cljsbuild will create a main.js in root folder. This can be run with
```shell
node main.js
```

## Options
Supports two options at the moment, and both needs an input file
- mirror: create a mirrored output of input file arg
- html: create escaped HTML from input file arg

## Examples
```shell
node main.js --mirror FILE > new-file.txt
node main.js --html FILE > index.html
```

### Bugs
The vector used to regex match ASCII symbols are currently very restricted to only use a selection of box drawing characters
The scope of this app is at the moment very narrow and was a companion app for my [website](https://roosta.sh) landing page.

## License

Copyright © 2016 Daniel Berg

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
