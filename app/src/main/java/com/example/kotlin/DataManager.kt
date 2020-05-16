package com.example.kotlin

object DataManager {

    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        inicializarCursos()
        inicializarNotas()
    }

    fun addNote(courseInfo: CourseInfo, noteTile: String, noteText: String): Int{
        val noteInfo = NoteInfo(courseInfo, noteTile, noteText)
        notes.add(noteInfo)
        return notes.lastIndex
    }

    fun findNote(courseInfo: CourseInfo, noteTile: String, noteText: String) : NoteInfo?{
        for (note in notes){
            if (courseInfo == note.course &&
                    noteTile == note.title &&
                    noteText == note.text)
                return note
        }
        return null
    }

    private fun inicializarCursos(){
        var course = CourseInfo("android_intens", "Android Programming with Intents")
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "Java Fundamentals", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core")
        courses.set(course.courseId, course)
    }

    fun inicializarNotas() {
        var note = NoteInfo(CourseInfo("android_intens", "Android Programming with Intents"),
            "das", "asdd")
        notes.add(note)

        note = NoteInfo(CourseInfo("android_intens", "Android Programming with Intents"),
            "Prueba", "Pruebas")
        notes.add(note)

        note = NoteInfo(CourseInfo("java_lang", "Java Fundamentals"),
            "SIIII", "NOOOO")
        notes.add(note)
    }
}