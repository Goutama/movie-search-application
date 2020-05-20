import {Component, OnInit} from '@angular/core';
import {SearchPageService} from './search-page.service';
import {SearchInput, TypeCast} from './search-page.model';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.scss']
})
export class SearchPageComponent implements OnInit {

  sourceName: string;
  targetName: string;
  requirement = 'typecast';
  requirements: string[] = ['typecast', 'coincidence', 'degree of separation'];
  errorMessage: string;
  typeCast: TypeCast;
  coincidenceMovieList: [];

  constructor(private searchPageService: SearchPageService) { }

  ngOnInit(): void {
  }

  search() {
    if (this.requirement === 'typecast') {
      this.searchPageService.getTypecast(
        {sourceName: this.sourceName}
        ).subscribe(
        (result) => {
          this.typeCast = result.body;
        },
        error => {
          this.errorMessage = error.error.message;
        }
      );
    } else if (this.requirement === 'coincidence') {
      this.searchPageService.getCoincidence(
        {
          sourceName: this.sourceName,
          targetName: this.targetName
        } as SearchInput
      ).subscribe(
        (result) => {
          this.coincidenceMovieList = result.body;
        },
        error => {
          this.errorMessage = error.error.message;
        }
      );
    }
  }
}
