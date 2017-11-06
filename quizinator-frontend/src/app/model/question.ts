import {Theme} from "./theme";
import {Tag} from "./tag";

export class Question{
  id: number;
  question: string;
  answer: string;
  theme: Theme;
  tags: Tag[];
}
