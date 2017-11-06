import {Component} from '@angular/core'
import {Theme} from "../model/theme";
import {ThemeService} from "../service/theme.service";
import {Subscription} from "rxjs/Subscription";
import {RouterModule} from "@angular/router";
import {Router} from "@angular/router";

@Component({
  selector: 'article',
  templateUrl: './theme.component.html',
  providers: [ThemeService]
})

export class ThemeViewComponent{
  private themes : Theme[];
  private newTheme : Theme = new Theme;

  constructor(private themeService: ThemeService, private router : Router){
  }

  ngOnInit(): void {
    this.getThemes();
  }

  public saveTheme() : void{
    this.themeService.saveTheme(this.newTheme).then(() => this.getThemes());
  }

  public deleteTheme(id) : void{
    this.themeService.deleteTheme(id).then(()=>this.getThemes());
  }

  private getThemes() : void{
    this.themeService.getThemes().then(themes => this.themes = themes);
  }
}
