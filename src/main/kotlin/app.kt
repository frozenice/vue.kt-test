/*
 * Copyright 2018 project contributors (see CONTRIBUTORS file)
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

import kotlinx.html.TagConsumer
import kotlinx.html.div
import kotlinx.html.li
import kotlinx.html.p
import vue.ComponentOptions
import vue.VueData
import vue.VueProps

fun main(args: Array<String>) {
  val app1 = App1.render()
  console.log("app1", app1)
  console.log("app1.\$el", app1.`$el`)
}

class AppData(val name: String, val todos: Array<String>) : VueData

object App1 : ComponentOptions<AppData, VueProps>() {
  override val el: String?
    get() = "#app1"

  override val template: (TagConsumer<String>.() -> Unit)?
    get() = {
      div {
        +"Hello {{name}}"
        component(App1Sub, SubProps(num = 23))
        component(App1Sub, SubProps(num = 42))
        li {
          attributes["v-for"] = "todo in todos"
          +"todo title: {{ todo }}"
        }
      }
    }

  override val data: AppData?
    get() = AppData(name = "tester", todos = arrayOf("first todo", "second todo"))
}

class SubProps(val num: Number) : VueProps

object App1Sub : ComponentOptions<VueData, SubProps>() {
  override val template: (TagConsumer<String>.() -> Unit)?
    get() = {
      p {
        +"Sub Test! num: {{num}}"
      }
    }
}
