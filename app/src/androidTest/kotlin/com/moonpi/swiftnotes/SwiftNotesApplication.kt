package com.moonpi.swiftnotes

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.util.getDevice
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matchers
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.step

const val MORE_BUTTON_DESCRIPTION = "Ещё"

class SwiftNotesApplication {

    fun checkApplicationTitle(text: String): SwiftNotesApplication {
        step("Проверяем заголовок приложения на соответствие строке: $text") {
            onView(withText(text)).check(matches(isDisplayed()))
        }
        deviceScreenshot("checkApplicationTitle_"+text.hashCode())
        return this
    }

    fun checkNoNotesPlaceholder(text: String): SwiftNotesApplication {
        step("Проверяем сообщение об отсутствии заметок на наличие и на соответствие строке: $text") {
            onView(withId(R.id.noNotes)).check(matches(isDisplayed()))
            onView(withId(R.id.noNotes)).check(matches(withText(text)))
        }
        deviceScreenshot("checkNoNotesPlaceholder_"+text.hashCode())
        return this
    }

    fun clickOnNewNoteButton(): SwiftNotesApplication {
        step("Нажимаем на кнопку(\"+\") для создания новой Заметки") {
            onView(withId(R.id.newNote)).check(matches(isDisplayed()))
            onView(withId(R.id.newNote)).perform(click())
        }
        deviceScreenshot("clickOnNewNoteButton")
        return this
    }

    fun checkNoteTitlePlaceholder(text: String): SwiftNotesApplication {
        step("Проверяем подсказку в поле для ввода название заметки на соответствие строке: $text") {
            onView(withId(R.id.titleEdit)).check(matches(withHint(text)))
        }
        deviceScreenshot("checkNoteTitlePlaceholder_"+text.hashCode())
        return this
    }

    fun checkNoteBodyPlaceholder(text: String): SwiftNotesApplication {
        step("Проверяем подсказку в поле для ввода содержания заметки на соответствие строке: $text") {
            onView(withId(R.id.bodyEdit)).check(matches(withHint(text)))
        }
        deviceScreenshot("checkNoteBodyPlaceholder_"+text.hashCode())
        return this
    }

    fun clickOnBackButton(): SwiftNotesApplication {
        step("Нажимаем на кнопку \"<\"(Назад)") {
            onView(
                allOf(
                    withClassName(containsString("ImageButton")),
                    withParent(withId(R.id.toolbarEdit))
                )
            ).perform(click())
        }
        deviceScreenshot("clickOnBackButton")
        return this
    }

    fun checkDialogMessage(text: String): SwiftNotesApplication {
        step("Проверяем текст диалогового сообщения на соответствие: $text") {
            onView(withId(android.R.id.content)).check(matches(isDisplayed()))
            onView(withId(android.R.id.message)).check(matches(withText(text)))
        }
        deviceScreenshot("checkDialogMessage_"+text.hashCode())
        return this
    }

    fun checkAcceptButtonOnDialog(): SwiftNotesApplication {
        step("Проверяем наличие кнопки \"YES\"(Принять) в диалоговом окне") {
            onView(withId(android.R.id.button1)).check(matches(isDisplayed()))
        }
        deviceScreenshot("checkAcceptButtonOnDialog")
        return this
    }

    fun checkCancelButtonOnDialog(): SwiftNotesApplication {
        step("Проверяем наличие кнопки \"NO\"(Отклонить) в диалоговом окне") {
            onView(withId(android.R.id.button2)).check(matches(isDisplayed()))
        }
        deviceScreenshot("checkCancelButtonOnDialog")
        return this
    }

    fun pressAcceptButtonOnDialog(): SwiftNotesApplication {
        step("Нажимаем на кнопку \"YES\"(Принять)") {
            onView(withId(android.R.id.button1)).perform(click())
        }
        deviceScreenshot("pressAcceptButtonOnDialog")
        return this
    }

    fun pressCancelButtonOnDialog(): SwiftNotesApplication {
        step("Нажимаем на кнопку \"NO\"(Отклонить)") {
            onView(withId(android.R.id.button2)).perform(click())
        }
        deviceScreenshot("pressCancelButtonOnDialog")
        return this
    }


    fun typeNoteTitle(text:String): SwiftNotesApplication {
        step("Вводим текст \"$text\" в поле для ввода названия заметки") {
            onView(withId(R.id.titleEdit))
                .perform(ViewActions.clearText())
                .perform(ViewActions.replaceText(text))
        }
        deviceScreenshot("typeNoteTitle_"+text.hashCode())
        return this
    }

    fun typeNoteBody(text:String): SwiftNotesApplication {
        step("Вводим текст \"$text\" в поле для ввода содержимого заметки") {
            onView(withId(R.id.bodyEdit))
                .perform(ViewActions.clearText())
                .perform(ViewActions.replaceText(text))
        }
        deviceScreenshot("typeNoteBody_"+text.hashCode())
        return this
    }

    fun checkNoteWithTitleAndBody(title:String, body:String): SwiftNotesApplication {
        step("Проверяем наличие заметки с заголовком \"$title\" и содержимым \"$body\"") {
            onView(Matchers.allOf(withId(R.id.titleView), withText(title)))
            onView(Matchers.allOf(withId(R.id.bodyView), withText(body)))
        }
        deviceScreenshot("checkNoteWithTitleAndBody_"+title.hashCode()+"_"+body.hashCode())
        return this
    }

    fun checkNotesCount(min:Int): SwiftNotesApplication {
        step("Проверяем, что количество заметок в списке больше $min") {
            onView(withId(R.id.listView)).check(matches(hasMinimumChildCount(min)))
        }
        deviceScreenshot("checkNotesCount_"+min)
        return this
    }

    fun pressMoreButton(): SwiftNotesApplication {
        step("Нажимаем на кнопку \"ЕЩЁ\"") {
            onView(withContentDescription(MORE_BUTTON_DESCRIPTION)).perform(click())
        }
        deviceScreenshot("pressMoreButton")
        return this
    }

    fun checkMenuItem(text:String): SwiftNotesApplication {
        step("Проверяем наличие пункта меню \"$text\"") {
            onView(
                Matchers.allOf(
                    withParent(withParent(withId(R.id.content))),
                    withId(R.id.title),
                    withText(text)
                )
            ).check(matches(isDisplayed()))
            deviceScreenshot("checkMenuItem_"+text.hashCode())
        }
        return this
    }

    fun pressBackButtonOnDevice(): SwiftNotesApplication {
        step("Нажимаем на кнопку Назад на устройстве") {
            getDevice().pressBack()
        }
        return this
    }
}
