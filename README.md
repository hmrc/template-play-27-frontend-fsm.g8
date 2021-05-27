HMRC Digital Scala Play 2.8 Stateful Frontend Microservice
===

A [Giter8](http://www.foundweekends.org/giter8/) template for creating HMRC Digital Scala Play 2.8 Stateful Frontend Microservice



How to create a new project based on the template?
---

* Go to directory where you want to create the template
* Decide your project name (the hardest part :))
* Run the command

    `sbt new hmrc/template-play-frontend-fsm.g8 --branch minimal --serviceName="Trader Services Route One Frontend" --serviceUrlPrefix="send-documents-for-customs-check" --serviceTargetPort="9379" --authorisedIdentifierKey="EORINumber" --serviceTitle="Send Documents For Customs Check" --authorisedServiceName="HMRC-CUS-ORG" --servicePrefix="Trader Services" --package="uk.gov.hmrc.traderservices" -o trader-services-route-one-frontend`

or    

* Install g8 commandline tool (http://www.foundweekends.org/giter8/setup.html)
* Run the command

    `g8 hmrc/template-play-frontend-fsm.g8 --branch minimal --serviceName="Trader Services Route One Frontend" --serviceUrlPrefix="send-documents-for-customs-check" --serviceTargetPort="9379" --authorisedIdentifierKey="EORINumber" --serviceTitle="Send Documents For Customs Check" --authorisedServiceName="HMRC-CUS-ORG" --servicePrefix="Trader Services" --package="uk.gov.hmrc.traderservices" -o trader-services-route-one-frontend`
    
and then
    
    cd trader-services-route-one-frontend
    git init
	git add .
	git commit -m start
  
* Test generated project using command 

    `sbt test it:test`
    

How to test the template and generate an example project?
---

* Run `./test.sh` 

An example project will be then created and tested in `target/sandbox/trader-services-route-one-frontend`

How to modify the template?
---

 * review template sources in `/src/main/g8`
 * modify files as you need, but be careful about placeholders, paths and so on
 * run `./test.sh` in template root to validate your changes
 
or (safer) ...

* run `./test.sh` first
* open `target/sandbox/trader-services-route-one-frontend` in your preferred IDE, 
* modify the generated example project as you wish, 
* build and test it as usual, you can run `sbt test it:test`,
* when you are done switch back to the template root
* run `./update-g8.sh` in order to port your changes back to the template.
* run `./test.sh` again to validate your changes

What is in the template?
--

Assuming the command above 
the template will supply the following values for the placeholders:

    $packaged$ -> uk/gov/hmrc/traderservices
	$package$ -> uk.gov.hmrc.traderservices
	$serviceName$ -> Trader Services Route One Frontend
	$serviceNameSnake$ -> TRADER_SERVICES_ROUTE_ONE_FRONTEND
	$serviceNameHyphen$ -> trader-services-route-one-frontend
	$serviceUrlPrefix$ -> send-documents-for-customs-check
	$servicePrefix$ -> Trader Services
	$servicePrefixCamel$ -> TraderServices
	$servicePrefixcamel$ -> traderServices
	$servicePrefixSnake$ -> TRADER_SERVICES
	$servicePrefixHyphen$ -> trader-services
	$authorisedServiceName$ -> HMRC-CUS-ORG
	$authorisedIdentifierKey$ -> EORINumber
	$serviceTargetPort$ -> 9379

and produce the folders and files as shown below:

    ├── .description
	├── .gitignore
	├── .scalafmt.conf
	├── app
	│   ├── assets
	│   │   ├── javascripts
	│   │   │   ├── .eslintrc
	│   │   │   ├── components
	│   │   │   │   ├── component.ts
	│   │   │   │   └── file-upload.ts
	│   │   │   │
	│   │   │   ├── index.ts
	│   │   │   ├── init.ts
	│   │   │   ├── legacy
	│   │   │   │   └── research-banner.js
	│   │   │   │
	│   │   │   ├── load-components.ts
	│   │   │   └── tsconfig.json
	│   │   │
	│   │   ├── karma.conf.js
	│   │   ├── package-lock.json
	│   │   ├── package.json
	│   │   ├── stylesheets
	│   │   │   ├── _autocomplete.scss
	│   │   │   ├── _currency-input.scss
	│   │   │   ├── _js-hidden.scss
	│   │   │   ├── _language-toggle.scss
	│   │   │   ├── _overrides.scss
	│   │   │   ├── _panel.scss
	│   │   │   ├── _percentage-input.scss
	│   │   │   ├── _print-preview.scss
	│   │   │   ├── _research-banner.scss
	│   │   │   ├── _responsive-table.scss
	│   │   │   ├── _summary-list.scss
	│   │   │   ├── _task-list.scss
	│   │   │   ├── _timeout-dialog.scss
	│   │   │   ├── application.scss
	│   │   │   ├── print-styles
	│   │   │   │   ├── _print-claim-details.scss
	│   │   │   │   └── _print.scss
	│   │   │   │
	│   │   │   ├── print.scss
	│   │   │   └── upload-loading.scss
	│   │   │
	│   │   ├── webpack.config.js
	│   │   ├── webpack.dev.config.js
	│   │   └── webpack.prod.config.js
	│   │
	│   ├── FrontendModule.scala
	│   └── uk
	│       └── gov
	│           └── hmrc
	│               └── traderservices
	│                   ├── connectors
	│                   │   └── FrontendAuthConnector.scala
	│                   │
	│                   ├── controllers
	│                   │   ├── AccessibilityStatementController.scala
	│                   │   ├── AuthActions.scala
	│                   │   ├── ContactFieldHelper.scala
	│                   │   ├── DateFieldHelper.scala
	│                   │   ├── FormFieldMappings.scala
	│                   │   ├── LanguageSwitchController.scala
	│                   │   ├── SessionController.scala
	│                   │   ├── SignOutController.scala
	│                   │   ├── Time12FieldHelper.scala
	│                   │   ├── Time24FieldHelper.scala
	│                   │   └── TraderServicesJourneyController.scala
	│                   │
	│                   ├── journeys
	│                   │   ├── TraderServicesJourneyModel.scala
	│                   │   └── TraderServicesJourneyStateFormats.scala
	│                   │
	│                   ├── models
	│                   │   ├── DeclarationDetails.scala
	│                   │   ├── EntryNumber.scala
	│                   │   ├── EnumerationFormats.scala
	│                   │   ├── EPU.scala
	│                   │   ├── ExampleQuestions.scala
	│                   │   ├── ExampleQuestionsStateModel.scala
	│                   │   ├── ExampleRequestType.scala
	│                   │   ├── ExampleRouteType.scala
	│                   │   ├── QuestionsAnswers.scala
	│                   │   ├── SealedTraitFormats.scala
	│                   │   ├── SimpleDecimalFormat.scala
	│                   │   └── SimpleStringFormat.scala
	│                   │
	│                   ├── repository
	│                   │   ├── CacheRepository.scala
	│                   │   └── JourneyCacheRepository.scala
	│                   │
	│                   ├── services
	│                   │   ├── AuditService.scala
	│                   │   ├── JourneyCache.scala
	│                   │   ├── MongoDBCachedJourneyService.scala
	│                   │   └── TraderServicesJourneyService.scala
	│                   │
	│                   ├── support
	│                   │   └── CallOps.scala
	│                   │
	│                   ├── views
	│                   │   ├── AccessibilityStatementView.scala.html
	│                   │   ├── components
	│                   │   │   ├── bullets.scala.html
	│                   │   │   ├── button.scala.html
	│                   │   │   ├── h1.scala.html
	│                   │   │   ├── h2.scala.html
	│                   │   │   ├── h3.scala.html
	│                   │   │   ├── html.scala
	│                   │   │   ├── languageSelection.scala.html
	│                   │   │   ├── link.scala.html
	│                   │   │   ├── orderedList.scala.html
	│                   │   │   ├── p.scala.html
	│                   │   │   └── strong.scala.html
	│                   │   │
	│                   │   ├── helpers
	│                   │   │   ├── CheckboxItemsHelper.scala
	│                   │   │   ├── CommonUtilsHelper.scala
	│                   │   │   ├── DateTimeFormatHelper.scala
	│                   │   │   ├── RadioItemsHelper.scala
	│                   │   │   └── ViewHelpers.scala
	│                   │   │
	│                   │   ├── PageNotFoundErrorView.scala.html
	│                   │   ├── StartView.scala.html
	│                   │   ├── templates
	│                   │   │   ├── ErrorTemplate.scala.html
	│                   │   │   ├── GovukLayoutWrapper.scala.html
	│                   │   │   ├── GtmBodySnippet.scala.html
	│                   │   │   └── GtmHeadSnippet.scala.html
	│                   │   │
	│                   │   ├── TimedOutView.scala.html
	│                   │   └── Views.scala
	│                   │
	│                   └── wiring
	│                       ├── AppConfig.scala
	│                       └── ErrorHandler.scala
	│
	├── build.sbt
	├── conf
	│   ├── app.routes
	│   ├── application-json-logger.xml
	│   ├── application.conf
	│   ├── logback.xml
	│   ├── messages.cy
	│   ├── messages.en
	│   └── prod.routes
	│
	├── it
	│   └── uk
	│       └── gov
	│           └── hmrc
	│               └── traderservices
	│                   ├── controllers
	│                   │   ├── AccessibilityStatementControllerISpec.scala
	│                   │   ├── AuthActionsISpec.scala
	│                   │   ├── LanguageSwitchControllerISpec.scala
	│                   │   ├── SessionControllerISpec.scala
	│                   │   └── TraderServicesJourneyISpec.scala
	│                   │
	│                   ├── services
	│                   │   └── MongoDBCachedJourneyServiceISpec.scala
	│                   │
	│                   ├── stubs
	│                   │   ├── AuthStubs.scala
	│                   │   ├── DataStreamStubs.scala
	│                   │   └── TraderServicesApiStubs.scala
	│                   │
	│                   └── support
	│                       ├── AppISpec.scala
	│                       ├── BaseISpec.scala
	│                       ├── InMemoryJourneyService.scala
	│                       ├── MetricsTestSupport.scala
	│                       ├── NonAuthPageISpec.scala
	│                       ├── Port.scala
	│                       ├── ServerISpec.scala
	│                       ├── TestAppConfig.scala
	│                       ├── TestData.scala
	│                       ├── TestJourneyService.scala
	│                       ├── UnitSpec.scala
	│                       └── WireMockSupport.scala
	│
	├── LICENSE
	├── project
	│   ├── build.properties
	│   ├── JavaScriptBuild.scala
	│   ├── Npm.scala
	│   ├── plugins.sbt
	│   └── Webpack.scala
	│
	├── README.md
	├── repository.yaml
	└── test
	    └── uk
	        └── gov
	            └── hmrc
	                └── traderservices
	                    ├── controllers
	                    │   ├── DateFieldHelperSpec.scala
	                    │   ├── Time12FieldHelperSpec.scala
	                    │   └── Time24FieldHelperSpec.scala
	                    │
	                    ├── journey
	                    │   ├── TraderServicesJourneyModelSpec.scala
	                    │   └── TraderServicesJourneyStateFormatsSpec.scala
	                    │
	                    ├── model
	                    │   ├── DeclarationDetailsSpec.scala
	                    │   └── ExampleQuestionsFormatSpec.scala
	                    │
	                    └── support
	                        ├── CallOpsSpec.scala
	                        ├── FormMappingMatchers.scala
	                        ├── FormMatchers.scala
	                        ├── FormValidator.scala
	                        ├── InMemoryStore.scala
	                        ├── JsonFormatTest.scala
	                        ├── StateMatchers.scala
	                        └── UnitSpec.scala