(DEFUN MEM (X LIST)
    (COND   ((NULL LIST) NIL)
            (T (COND    ((EQ X (CAR LIST)) T)
                        (T (MEM X (CDR LIST)))
               )
            )
    )
)

(MEM 3 (QUOTE (2 3 4)))
(MEM 2 (QUOTE ()))
(MEM 2 (QUOTE (4 5 6 7 8 9 10 12 13)))