with Ada.Text_IO; use Ada.Text_IO;

procedure Main is
   thread_count : constant Integer  := 3;

   task type Counter is
      entry start (id, step : in Integer);
      entry stop;
   end Counter;

   task body Counter is
      should_stop : Boolean := False;

      step       : Long_Long_Integer;
      sum        : Long_Long_Integer := 0;
      step_count : Long_Long_Integer := 0;
      id         : Integer;
   begin
      accept start (id, step : in Integer) do
         Counter.id   := id;
         Counter.step := Long_Long_Integer (step);
      end start;
      while not should_stop loop
         select
            accept stop do
               should_stop := True;
            end stop;
         else
            sum        := sum + step;
            step_count := step_count + 1;
         end select;
      end loop;
      Put_Line
        ("Counter" & id'Img & " with step" & step'Img &
         " has just finished. Sum:" & sum'Img & "; Steps taken:" &
         step_count'Img);
   end Counter;

   counters : array (1 .. thread_count) of Counter;

   task type Stoper is
      entry start (counter_task_index : in Integer; end_in : in Duration);
   end Stoper;

   task body Stoper is
      counter_task_index : Integer;
      end_in             : Duration;
   begin
      accept start (counter_task_index : in Integer; end_in : in Duration) do
         Stoper.counter_task_index := counter_task_index;
         Stoper.end_in             := end_in;
      end start;
      delay end_in;
      counters (counter_task_index).stop;
   end Stoper;

   stopers : array (1 .. thread_count) of Stoper;

begin
   for I in 1 .. thread_count loop
      counters (I).start (id => I, step => I);
      stopers (I).start (counter_task_index => I, end_in => Duration (I));
   end loop;

end Main;
