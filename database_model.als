sig Student {
    id: lone String,
    name: String,
    email: String,
    classes: set Classes
}

sig Teacher {
    id: lone String,
    name: String,
    email: String
}

sig Classes {
    id: lone String,
    title: String,
    description: String,
    students: set Student,
    teacher: lone Teacher
}

fact {
    // Constraints
    all s: Student | s.id != none
    all t: Teacher | t.id != none
    all c: Classes | c.id != none && c.title != none && c.description != none

    // Relationships
    all s: Student, c: Classes | s in c.students <=> c in s.classes
    all c: Classes | c.teacher in Teacher
    all s1, s2: Student, c: Classes | s1 != s2 && s1 in c.students && s2 in c.students => s1.classes != s2.classes
}

assert desiredProperty {
    all c: Classes | #c.students >= 2
}

assert eachStudentEnrolled {
    all s: Student | some c: Classes | s in c.students
}

assert uniqueTeacher {
    all c: Classes | lone c.teacher
}

assert noDuplicateStudents {
    all c: Classes | no disj s1, s2: c.students | s1 = s2
}
