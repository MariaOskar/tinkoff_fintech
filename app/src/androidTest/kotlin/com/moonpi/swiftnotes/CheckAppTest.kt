package com.moonpi.swiftnotes

import android.os.Environment
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.annotations.Description
import ru.tinkoff.allure.annotations.DisplayName
import java.io.File

const val TITLE = "Swiftnotes"
const val NO_NOTES_DESCRIPTION = "Press '+' to add new note"
const val NOTE_TITLE_FIELD_TEXT = "Title"
const val NOTE_BODY_FIELD_TEXT = "Note"
const val DIALOG_TEXT = "Save changes?"
const val NOTE_TITLE = "Заметка 1"
const val NOTE_BODY = "Тестовая запись 1"
const val HIDE_NOTE_TEXT = "Hide note body in list"
const val NOTE_FONT_SIZE_TEXT = "Note font size"
const val RATE_APP_TEXT = "Rate app"
const val RESTORE_NOTES_TEXT = "Restore notes"
const val BACKUP_NOTES_TEXT = "Backup notes"

@RunWith(AndroidJUnit4::class)
class CheckAppTest{

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    var app = SwiftNotesApplication();

    @Test()
    @DisplayName("Проверка состава активити и наличия диалогового окна при выходе с формы редактирования заметки")
    fun checkAppTest() {
        app
            .checkApplicationTitle(TITLE)
            .checkNoNotesPlaceholder(NO_NOTES_DESCRIPTION)
            .clickOnNewNoteButton()
            .checkNoteTitlePlaceholder(NOTE_TITLE_FIELD_TEXT)
            .checkNoteBodyPlaceholder(NOTE_BODY_FIELD_TEXT)
            .clickOnBackButton()
            .checkDialogMessage(DIALOG_TEXT)
            .checkAcceptButtonOnDialog()
            .checkCancelButtonOnDialog()
            .pressCancelButtonOnDialog()
    }


    @Test
    @DisplayName("Проверка создания заметки и её наличия в списке заметок после сохранения")
    fun createNoteTest() {
        app
            .clickOnNewNoteButton()
            .typeNoteTitle(NOTE_TITLE)
            .typeNoteBody(NOTE_BODY)
            .clickOnBackButton()
            .pressAcceptButtonOnDialog()
            .checkNotesCount(1)
            .checkNoteWithTitleAndBody(NOTE_TITLE,NOTE_BODY )
    }

    @Test
    @DisplayName("Проверка содержимого меню на начальном экране и при редактировании заметки")
    fun checkMenuTest() {
        app
            .pressMoreButton()
            .checkMenuItem(BACKUP_NOTES_TEXT)
            .checkMenuItem(RESTORE_NOTES_TEXT)
            .checkMenuItem(RATE_APP_TEXT)
            .pressBackButtonOnDevice()
            .clickOnNewNoteButton()
            .pressMoreButton()
            .checkMenuItem(NOTE_FONT_SIZE_TEXT)
            .checkMenuItem(HIDE_NOTE_TEXT)
    }

    companion object {
        val ALLURE_PATH = Environment.getExternalStorageDirectory().absolutePath + "/allure-results"
        val CONTEXT_CACHE_PATH = InstrumentationRegistry.getContext().cacheDir.absolutePath
        val TARGET_CONTEXT_CACHE_PATH = InstrumentationRegistry.getTargetContext().cacheDir.absolutePath

        @BeforeClass
        @JvmStatic
        fun prepare() {
            listOf(
                ALLURE_PATH,
                CONTEXT_CACHE_PATH,
                TARGET_CONTEXT_CACHE_PATH
            ).forEach { path -> clearDirectory(path) }
        }

        fun clearDirectory(path:String){
            val directory = File(path)
            if (directory.exists())
                directory.deleteRecursively()
        }
    }
}
