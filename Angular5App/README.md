# NotificationApplication

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.7.

npm install @angular/cli -g
npm install bootstrap4-plus-jquery --save
npm install jquery --save
npm install ngx-bootstrap --save
npm install socket.io-client --save
npm install @types/socket.io-client
npm install -g http-server

ng new Notification-Application --style=scss --routing
ng add @angular/pwa --project Notification-Application

ng build --prod
cd dist
http-server -c-1 --proxy http://localhost:3000 .
## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
