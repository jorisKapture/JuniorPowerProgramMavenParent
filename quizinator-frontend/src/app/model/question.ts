import {Theme} from "./theme";
import {Tag} from "./tag";

export class Question {
  id: number;
  question: string;
  answer: string;
  url: string;
  theme: Theme;
  tags: Tag[];
  picture: string;
}
