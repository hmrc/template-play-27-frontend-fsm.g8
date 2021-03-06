/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package $package$.controllers

import scala.concurrent.Future

import javax.inject.{Inject, Singleton}
import play.api.mvc.{Action, AnyContent, MessagesControllerComponents}
import $package$.views.html.AccessibilityStatementView
import uk.gov.hmrc.play.bootstrap.frontend.controller.FrontendController

@Singleton
class AccessibilityStatementController @Inject() (
  controllerComponents: MessagesControllerComponents,
  accessibilityStatementView: AccessibilityStatementView
) extends FrontendController(controllerComponents) {

  val showPage: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(accessibilityStatementView()))
  }
}
