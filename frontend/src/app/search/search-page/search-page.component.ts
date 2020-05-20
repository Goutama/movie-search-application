import {Component, OnInit} from '@angular/core';
import {SearchPageService} from './search-page.service';
import {Coincidence, LinkLevel, SearchInput, TypeCastInfo} from './search-page.model';
import {finalize} from 'rxjs/operators';

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

  typeCastInfo: TypeCastInfo;
  coincidence: Coincidence;
  linkLevel: LinkLevel;

  isLoading = false;

  constructor(private searchPageService: SearchPageService) {
  }

  ngOnInit(): void {
  }

  search() {
    this.isLoading = true;
    if (this.requirement === 'typecast') {
      this.getTypecastInfo();
    } else if (this.requirement === 'coincidence') {
      this.getCoincidence();
    } else {
      this.getLinkLevel();
    }
  }

  getTypecastInfo() {
    this.searchPageService.getTypecast(
      {sourceName: this.sourceName}
    ).pipe(
      finalize(() => this.isLoading = false),
    ).subscribe(
      (result) => {
        this.typeCastInfo = result.body;
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  getCoincidence() {
    this.searchPageService.getCoincidence(
      {
        sourceName: this.sourceName,
        targetName: this.targetName
      } as SearchInput
    ).pipe(
      finalize(() => this.isLoading = false),
    ).subscribe(
      (result) => {
        this.coincidence = result.body;
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  getLinkLevel() {
    this.searchPageService.getLinkLevel(
      {
        sourceName: this.sourceName,
        targetName: this.targetName
      } as SearchInput
    ).pipe(
      finalize(() => this.isLoading = false),
    ).subscribe(
      (result) => {
        this.linkLevel = result.body;
      },
      error => {
        this.errorMessage = error.error.message;
      }
    );
  }

  resetValues() {
    this.sourceName = '';
    this.targetName = '';
    this.errorMessage = '';
    this.typeCastInfo = null;
    this.coincidence = null;
    this.linkLevel = null;
    if (this.requirement === 'degree of separation') {
      this.targetName = 'Kevin Bacon';
    }
  }
}
