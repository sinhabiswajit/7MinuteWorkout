package com.biswa.a7minuteworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        // 1
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJacks)
        // 2
        val wallSit = ExerciseModel(
            2,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)
        // 3
        val pushUp = ExerciseModel(
            3,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUp)
        // 4
        val abdominalCrunch = ExerciseModel(
            4,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)
        // 5
        val highKneesRunningInPlace = ExerciseModel(
            5,
            "High Knees Running in Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(highKneesRunningInPlace)
        // 6
        val lunge = ExerciseModel(
            6,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunge)
        // 7
        val plank = ExerciseModel(
            7,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)
        // 8
        val pushUpAndRotation = ExerciseModel(
            8,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(pushUpAndRotation)
        // 9
        val sidePlank = ExerciseModel(
            9,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)
        // 10
        val squat = ExerciseModel(
            10,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squat)
        // 11
        val stepUpOntoChair = ExerciseModel(
            11,
            "Step Up onto Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUpOntoChair)
        // 12
        val tricepDipsOnChair = ExerciseModel(
            12,
            "Tricep Dips on Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(tricepDipsOnChair)

        return exerciseList
    }
}