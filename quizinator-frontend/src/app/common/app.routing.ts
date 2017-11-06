import {RouterModule, Routes} from "@angular/router";
import {IRouting} from './routing.interface'

import { AppComponent } from '../app.component';
import { InputViewComponent} from "../input/inputview.component";
import {TagViewComponent} from "../tag/tag.component";
import {ThemeViewComponent} from "../theme/theme.component";
import {QuestionViewComponent} from "../question/question.component";

const routes: Routes =[
  {path: 'input', component: InputViewComponent},
  {path: 'tag', component: TagViewComponent},
  {path: 'theme', component: ThemeViewComponent},
  {path: 'question', component: QuestionViewComponent}
];

export const AppRouting: IRouting = {
  routes: RouterModule.forRoot(routes),
  components: []
};
